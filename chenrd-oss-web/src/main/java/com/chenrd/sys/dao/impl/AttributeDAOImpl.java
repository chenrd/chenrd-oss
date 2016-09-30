/*
 * 文件名：AttributeDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月9日
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
import com.chenrd.sys.dao.AttributeDAO;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.service.Status;

/**
 * 
 * @author chenrd
 * @version 2015年7月9日
 * @see AttributeDAOImpl
 * @since
 */
@SuppressWarnings("unchecked")
@Repository("attributeDAO")
public class AttributeDAOImpl extends AbstractBaseDAO<Attribute> implements AttributeDAO
{

    @Override
    public List<Attribute> findParent(String applyKey)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(Attribute.class.getSimpleName()).append(" as po where po.parentKey = :applyKey and po.status >= :status order by po.key");
        params.put("applyKey", applyKey);
        params.put("status", Status.OFF);
        return (List<Attribute>) super.find(hql.toString(), params);
    }

    @Override
    public List<Attribute> findPaging(String parentKey, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("from ").append(Attribute.class.getSimpleName()).append(" as po where po.parentKey = :parentKey and po.status >= :status order by po.key");
        params.put("parentKey", parentKey);
        params.put("status", Status.OFF);
        return (List<Attribute>) super.findPaging(hql.toString(), "select count(*) " + hql.toString(), params, paging);
    }
}
