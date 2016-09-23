/*
 * 文件名：OrganizationManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.sys.service.info.OrgInfo;

/**
 * 
 * 组织机构
 * @author chenrd
 * @version 2015年5月15日
 * @see OrganizationManager
 * @since
 */
public interface OrganizationManager
{
    
    /**
     * 
     * @param id 
     * @param paging 分页条件
     * @return List<OrgInfo>
     * @see
     */
    List<OrgInfo> find(String id, Paging paging);
}
