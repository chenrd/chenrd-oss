/*
 * 文件名：UserService.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.List;

import com.chenrd.sys.service.info.BaseUserInfo;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月14日
 * @see UserService
 * @since
 */
public interface UserService
{
    
	/**
	 * 获取全部，只获取到id,username,name属性
	 * 
	 * @return 
	 * @see
	 */
    List<UserInfo> findSelect();
    
    /**
     * 查询拥有指定字段权限的用户集合，只返回id,username字段的值
     * @return 
     * @see
     */
    List<UserInfo> findFieldByPowerKey(String key);
    
    /**
     * 保存一个
     * 
     * @return 
     * @see
     */
    String saveOrUpdate(BaseUserInfo userInfo, String applyKey);
    
    /**
     * 
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return 用户
     * @see
     */
    UserInfo findPowerByUsername(String username, String applyKey);
    
    void delete(String id);
    
    /**
     * 重置密码
     * @see
     */
    void resetPassword(String id, String username);
    
    /**
     * 修改密码
     * 
     * @param old 旧密码
     * @param newPassword 新密码
     * @return 
     * @see
     */
    int modifyPassword(String username, String old, String newPassword);
    
    /**
     * 分配字段权限
     * 
     * @param userId
     * @param fieldId 
     * @see
     */
    void allotField(String userId, Long fieldId);
    
    void deleteField(String userId, Long fieldId);
    
    /**
     * 分配字段权限
     * 
     * @param userId
     * @param fieldId 
     * @see
     */
    void allotFields(String[] userIds, Long fieldId);
    
    void deleteFields(String[] userIds, Long fieldId);
}
