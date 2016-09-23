/*
 * 文件名：AttributeDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.dao.QueryParamDAO;
import com.chenrd.sys.entity.Attribute;

/**
 * 
 * @author chenrd
 * @version 2015年7月9日
 * @see AttributeDAO
 * @since
 */
public interface AttributeDAO extends QueryParamDAO
{
    
    /**
     * @param applyKey
     * @return 
     * @see
     */
    List<Attribute> findParent(String applyKey); 
    
    
    /**
     * 分页查询素具
     * 
     * @param parentKey
     * @param paging
     * @return 
     * @see
     */
    List<Attribute> findPaging(String parentKey, Paging paging);
    
}
