/*
 * 文件名：FuncManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 
 * @author chenrd
 * @version 2015年6月16日
 * @see FuncManager
 * @since
 */
public interface FuncManager extends PowerBusiness {

	/**
	 * 查询
	 * 
	 * @param username
	 * @return
	 * @see
	 */
	List<PowerInfo> findByUsername(String username);

	/**
	 * 获取指定用户所有的字段权限 用于用户字段权限分配
	 * 
	 * @return
	 * @see
	 */
	List<AttributeVO> findUserAllAttrPowers(String username);

	/**
	 * @param key 标识
	 * @return ""
	 * @see
	 */
	PowerInfo getByKey(String key);

	/**
	 * @param id ""
	 * @return ""
	 * @see
	 */
	PowerInfo get(Long id);

	/**
	 * 
	 * 保存更新
	 * 
	 * @param powerInfo ""
	 * @see
	 */
	void saveOrUpdate(PowerInfo powerInfo);

}
