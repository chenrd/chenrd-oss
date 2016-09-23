/*
 * 文件名：RoleDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
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
import com.chenrd.sys.dao.RoleDAO;
import com.chenrd.sys.entity.Role;
import com.chenrd.sys.service.Status;

/**
 * 
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleDAOImpl
 * @since
 */
@Repository("roleDAO")
public class RoleDAOImpl extends QueryParamsBaseDAO<Role> implements RoleDAO
{

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> findPaging(String name, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder().append("from ").append(Role.class.getSimpleName()).append(" as po where").append(" po.status>=:status");
        params.put("status", Status.OFF);
        if (StringUtils.isNotBlank(name))
        {
            hql.append(" and po.name like :name");
            params.put("name", name + "%");
        }
        hql.append(" order by po.name");
        return (List<Role>) findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Role getByKey(String key)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(Role.class.getSimpleName()).append(" as po where ").append(" po.status>=:status and po.key=:key");
        params.put("status", Status.NO);
        params.put("key", key);
        List<Role> list = (List<Role>) super.find(hql.toString(), params);
        return list == null || list.size() == 0 ? null : list.get(0);
    }

    @Override
    public Class<Role> getDomClass()
    {
        return Role.class;
    }
    
    
}
