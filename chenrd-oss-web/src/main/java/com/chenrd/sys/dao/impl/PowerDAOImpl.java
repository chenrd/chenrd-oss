/*
 * 文件名：PowerDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月27日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chenrd.common.Paging;
import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.PowerDAO;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.service.PowerType;
import com.chenrd.sys.service.Status;

/**
 * 
 * @author chenrd
 * @version 2015年5月27日
 * @see PowerDAOImpl
 * @since
 */
@SuppressWarnings("unchecked")
@Repository("powerDAO")
public class PowerDAOImpl extends AbstractBaseDAO<Power> implements PowerDAO
{

    @Override
    public void addPower(Attribute power)
    {
        Attribute parent = getByProperties(Attribute.class, "key", power.getKey());
        if (parent == null)
        {
            parent = new Attribute();
            parent.setName(power.getName());
            parent.setKey(power.getKey());
            parent.setType(power.getType());
            parent.setApply(power.getApply());
            parent.setParentKey(power.getApply().getKey());
            super.getSession().save(parent);
        }
        
        Query query = super.getSession().createQuery("select count(*) from " + Power.class.getSimpleName() + " as po where po.value=:value and po.parentKey = :parentKey");
        query.setString("value", power.getValue());
        query.setString("parentKey", parent.getKey());
        List<Long> count = query.list();
        if (count != null && count.size() > 0 && count.get(0) > 0) 
        {
            return;
        }
        query = super.getSession().createQuery("select max(po.key) from " + Power.class.getSimpleName() + " as po where po.parentKey=:key"); 
        query.setString("key", power.getKey());
        List<String> list = query.list();
        int order = 0;
        if (list == null || list.size() == 0 || list.get(0) == null) 
        {
            order = 100;
        }
        else
        {
            String[] s = list.get(0).split("/");
            Integer.parseInt(s[s.length - 1]);
        }
        power.setParentKey(parent.getKey());
        power.setKey(parent.getKey() + "/" + (++order));
        power.setName(power.getValue());
        super.getSession().saveOrUpdate(power);
    }

    @Override
    public List<Power> findPaging(Long applyId, String parentKey, int type, Paging paging)
    {
        StringBuilder hql = new StringBuilder("from ").append(Power.class.getSimpleName())
            .append(" as po where po.type=:type and po.apply.id = :applyId and po.parentKey=:parentKey and po.status>=:status order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", type);
        params.put("applyId", applyId);
        params.put("parentKey", parentKey);
        params.put("status", Status.OFF);
        return (List<Power>) super.findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging); 
    }

    @Override
    public List<Power> findAll(int type)
    {
        StringBuilder hql = new StringBuilder("from ").append(Power.class.getSimpleName()).append(" as po where po.type=:type and po.status=:status order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", type);
        params.put("status", Status.NO);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Power> findFilter(String applyKey)
    {
        StringBuilder hql = new StringBuilder("from ").append(Power.class.getSimpleName())
            .append(" as po where po.apply.key=:applyKey and po.status=:status and (po.type = :type1 or po.type = :type2) order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type1", PowerType.MENU_POWER);
        params.put("type2", PowerType.FUNC_POWER);
        params.put("applyKey", applyKey);
        params.put("status", Status.NO);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Power> findUserFuncPower(String username, String applyKey)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName())
            .append(" as user inner join user.powers as po with po.key like :applyKey and po.status=:status and po.type=:type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FUNC_POWER);
        params.put("applyKey", applyKey + "%");
        params.put("status", Status.NO);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Power> findRoleFuncPower(String username, String applyKey)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.roles as role with role.status=:status inner join role.powers as po with po.status=:status and po.type =:type and po.key like :applyKey")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FUNC_POWER);
        params.put("applyKey", applyKey + "%");
        params.put("status", Status.NO);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }
    
    @Override
    public List<Power> findUserFieldPower(String username, String applyKey)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.powers as po with po.key like :applyKey and po.status=:status and po.type=:type and po.attrType=:attrType")
            .append(" where user.status=:status and user.username=:username");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FIELD_POWER);
        params.put("applyKey", applyKey + "%");
        params.put("status", Status.NO);
        params.put("attrType", 3);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }


    @Override
    public List<Power> findByApplyKeyUsername(String applyKey, String username, int powerType)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName())
            .append(" as user inner join user.powers as po with po.key like :applyKey and po.status=:status and po.type = :type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", powerType);
        params.put("applyKey", applyKey + "%");
        params.put("status", Status.NO);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Power> findRoleFuncByUsername(String username)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.roles as role with role.status=:status inner join role.powers as po with po.status=:status and po.type = :type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FUNC_POWER);
        params.put("status", Status.NO);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Power> findUserFuncByUsername(String username)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName())
            .append(" as user inner join user.powers as po with po.status=:status and po.type = :type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FUNC_POWER);
        params.put("status", Status.NO);
        params.put("username", username);
        return (List<Power>) super.find(hql.toString(), params);
    }

    @Override
    public List<Attribute> findUserFieldPower(String username)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.powers as po with po.status=:status and po.type=:type and po.attrType=:attrType")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("type", PowerType.FIELD_POWER);
        params.put("status", Status.NO);
        params.put("attrType", 3);
        params.put("username", username);
        return (List<Attribute>) super.find(hql.toString(), params);
    }

}
