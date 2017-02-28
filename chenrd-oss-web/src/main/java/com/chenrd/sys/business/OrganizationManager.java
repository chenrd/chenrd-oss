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

import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.vo.OrganizationVO;

/**
 * 
 * 组织机构
 * @author chenrd
 * @version 2015年5月15日
 * @see OrganizationManager
 * @since
 */
public interface OrganizationManager extends PowerBusiness {
    
	Long DEFUALT_ORG_ID = 1l;
	String DEFUALT_ORG_NAME = "ROOT";
	
	void saveOrUpdate(OrganizationVO vo);
}
