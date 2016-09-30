/*
 * 文件名：MenuDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.chenrd.common.Paging;
import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.MenuDAO;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.service.PowerType;
import com.chenrd.sys.service.Status;
import com.chenrd.sys.vo.MenuVO;

/**
 * 
 * 菜单
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuDAOImpl
 * @since
 */
@SuppressWarnings("unchecked")
@Repository("menuDAO")
public class MenuDAOImpl extends AbstractBaseDAO<Menu> implements MenuDAO
{
    
    @Override
    public List<Menu> findChilds(Long applyId, String parentKey, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(Menu.class.getSimpleName()).append(" as po where po.status >= :status and po.apply.id = :apply");
        if (StringUtils.isNotBlank(parentKey)) 
        {
            hql.append(" and po.parentKey = :parentKey");
            params.put("parentKey", parentKey);
        }
        else
        {
            hql.append(" and po.parentKey is null");
        }
        hql.append(" order by po.key");
        params.put("status", Status.OFF);
        params.put("apply", applyId);
        if (paging != null)
        {
            return (List<Menu>) super.findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging);
        }
        else
        {
            return (List<Menu>) super.find(hql.toString(), params);
        }
        
    }

    @Override
    public Menu getByKey(String key)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(Menu.class.getSimpleName()).append(" as po where po.status >= :status and po.key = :key");
        params.put("status", Status.OFF);
        params.put("key", key);
        List<Menu> list = (List<Menu>) find(hql.toString(), params);
        return list == null || list.size() == 0 ? null : list.get(0);
    }

    @Override
    public List<MenuVO> findAll()
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("select new com.chenrd.sys.vo.MenuVO(po.id, po.key, po.name, po.fullName) from ")
            .append(Menu.class.getSimpleName()).append(" as po where po.status = :status order by po.key");
        params.put("status", Status.NO);
        return (List<MenuVO>) find(hql.toString(), params);
    }

    @Override
    public List<String> queryFullNameByUrl(String url)
    {
        StringBuilder hql = new StringBuilder("select po.fullName from ").append(Menu.class.getSimpleName()).append(" as po where po.url = :url");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("url", url);
        Query query = super.getSession().createQuery(hql.toString());
        query.setProperties(params);
        return (List<String>) query.list();
    }

    @Override
    public List<Menu> findRolePowerByUsername(String username, String applyKey)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
                .append(" inner join user.roles as role with role.status=:status inner join role.powers as po with po.status=:status and po.type=:type and po.key like :applyKey")
                .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("status", Status.NO);
        params.put("type", PowerType.MENU_POWER);
        params.put("applyKey", applyKey + "%");
        params.put("username", username);
        return (List<Menu>) super.find(hql.toString(), params);
    }

    @Override
    public List<Menu> findUserPowerByUsername(String username, String applyKey)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.powers as po with po.status=:status and po.type=:type and po.key like :applyKey")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("status", Status.NO);
        params.put("type", PowerType.MENU_POWER);
        params.put("applyKey", applyKey + "%");
        params.put("username", username);
        return (List<Menu>) super.find(hql.toString(), params);
    }

    @Override
    public List<Menu> findRolePowerByUsername(String username)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.roles as role with role.status=:status inner join role.powers as po with po.status=:status and po.type=:type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("status", Status.NO);
        params.put("type", PowerType.MENU_POWER);
        params.put("username", username);
        return (List<Menu>) super.find(hql.toString(), params);
    }

    @Override
    public List<Menu> findUserPowerByUsername(String username)
    {
        StringBuilder hql = new StringBuilder("select distinct po from ").append(User.class.getSimpleName()).append(" as user")
            .append(" inner join user.powers as po with po.status=:status and po.type=:type")
            .append(" where user.status=:status and user.username=:username order by po.key");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("status", Status.NO);
        params.put("type", PowerType.MENU_POWER);
        params.put("username", username);
        return (List<Menu>) super.find(hql.toString(), params);
    }
   
}
