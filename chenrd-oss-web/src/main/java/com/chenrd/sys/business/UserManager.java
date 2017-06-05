/*
 * 文件名：UserManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.vo.UserVO;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserManager
 * @since
 */
public interface UserManager extends PowerBusiness {

	/**
	 * 
	 * 保存用户
	 * 
	 * @param userInfo
	 *            用户信息
	 * @see
	 */
	String saveOrUpdate(UserVO userInfo);

	/**
	 * 获取用户全部关联属性
	 * 
	 * @param id
	 * @return
	 * @see
	 */
	UserVO getUserAndPower(String id);

	/**
	 * 
	 * 授权
	 * 
	 * @param id
	 * @param roles
	 * @param powers
	 * @see
	 */
	void allot(String id, String[] roles, String[] powers, String[] applys);

	/**
	 * 修改密码
	 * 
	 * @param username
	 *            用户名
	 * @param oldPassword
	 *            久密码
	 * @param newPassword
	 *            新密码
	 * @see
	 */
	void updatePassword(String username, String oldPassword, String newPassword);

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @see
	 */
	void resetPassword(String id, String username);

	/**
     * 分配字段权限
     * 
     * @param userId
     * @param fieldId 
     * @see
     */
    void allotField(Long fieldId, String... userId);
    
    void deleteField(Long fieldId, String... userId);
}
