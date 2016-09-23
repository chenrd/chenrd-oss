/*
 * 文件名：UserDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
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
import org.springframework.stereotype.Repository;

import com.chenrd.common.Paging;
import com.chenrd.dao.abs.QueryParamsBaseDAO;
import com.chenrd.sys.dao.UserDAO;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.service.Status;



/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserDAOImpl
 * @since
 */
@SuppressWarnings("unchecked")
@Repository("userDAO")
public class UserDAOImpl extends QueryParamsBaseDAO<User> implements UserDAO
{

    
    @Override
    public List<User> findPaging(String name, String phone, String username, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(User.class.getSimpleName()).append(" as po where po.status >= :status");
        params.put("status", Status.OFF);
        if (StringUtils.isNotBlank(name)) 
        {
            hql.append(" and po.name like :name");
            params.put("name", name + "%");
        }
        
        if (StringUtils.isNotBlank(phone)) 
        {
            hql.append(" and po.name like :phone");
            params.put("phone", phone + "%");
        }
        
        if (StringUtils.isNotBlank(username))
        {
            hql.append(" and po.createUser=:username");
            params.put("username", username);
        }
        
        hql.append(" order by po.name");
        return (List<User>) findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging);
    }

    @Override
    public void logicDelete(String id)
    {
        User user = (User) super.getSession().get(User.class, id);
        if (user == null) 
        {
            throw new RuntimeException("删除用户失败，id=[" + id + "]的用户不存在");
        }
        user.setStatus(-1);
        super.getSession().update(user);
    }

    @Override
    public Long countByUserName(String username)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("select count(*) from ").append(User.class.getSimpleName()).append(" as po where po.status>=:status and po.username=:username");
        params.put("status", Status.OFF);
        params.put("username", username);
        return super.count(hql.toString(), params);
    }

    @Override
    public Class<User> getDomClass()
    {
        return User.class;
    }
    
}
