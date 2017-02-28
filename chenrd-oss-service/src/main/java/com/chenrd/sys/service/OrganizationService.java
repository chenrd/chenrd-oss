/*
 * 文件名：OrganizationService.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.List;

import com.chenrd.sys.service.info.OrganizationInfo;

public interface OrganizationService {
	
	/**
	 * 获取到组织结构树枝
	 * @return 
	 * @see
	 */
	List<OrganizationInfo> findSelect();
	
}
