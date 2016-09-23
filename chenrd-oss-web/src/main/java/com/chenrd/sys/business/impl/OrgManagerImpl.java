/*
 * 文件名：OrganizationManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.Paging;
import com.chenrd.sys.business.OrganizationManager;
import com.chenrd.sys.dao.OrganizationDAO;
import com.chenrd.sys.service.info.OrgInfo;

/**
 * 
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author chenrd
 * @version 2015年5月21日
 * @see OrgManagerImpl
 * @since
 */
@Transactional
@Service("orgManager")
public class OrgManagerImpl implements OrganizationManager
{

    /**
     * 
     */
    @Resource(name = "orgDAO")
    private OrganizationDAO orgDAO;
    
    @Override
    public List<OrgInfo> find(String id, Paging paging)
    {
        return null;
    }

}
