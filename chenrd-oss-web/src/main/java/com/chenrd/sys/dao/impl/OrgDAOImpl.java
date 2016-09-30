/*
 * 文件名：OrgDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chenrd.common.Paging;
import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.OrganizationDAO;
import com.chenrd.sys.entity.Organization;

/**
 * 
 * @author chenrd
 * @version 2015年7月4日
 * @see OrgDAOImpl
 * @since
 */
@Repository("orgDAO")
public class OrgDAOImpl extends AbstractBaseDAO<Organization> implements OrganizationDAO
{

    @SuppressWarnings("unchecked")
    @Override
    public List<Organization> find(String id, Paging paging)
    {
        StringBuilder hql = new StringBuilder("from ").append(Organization.class.getSimpleName()).append(" as po where po.parent.id = :parentId order by po.order");
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("parentId", id);
        if (paging == null)
        {
            return (List<Organization>) super.find(hql.toString(), params);
        }
        else
        {
            return (List<Organization>) super.findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging);
        }
    }
    
}
