/*
 * 文件名：OrganizationDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.sys.entity.Organization;

/**
 * 
 * @author chenrd
 * @version 2015年7月4日
 * @see OrganizationDAO
 * @since
 */
public interface OrganizationDAO
{
    /**
     * 
     * @param id 
     * @param paging 分页条件
     * @return List<OrgInfo>
     * @see
     */
    List<Organization> find(String id, Paging paging);
}
