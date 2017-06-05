/*
 * 文件名：RoleManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.service.info.RoleInfo;

/**
 * 
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleManager
 * @since
 */
public interface RoleManager extends PowerBusiness {

	/**
	 * 
	 * 添加角色
	 * 
	 * @param info
	 * @param powers
	 * @return
	 * @see
	 */
	void saveOrUpdate(RoleInfo info, Long[] powers);

	RoleInfo get(Long id);

}
