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
    
    List<UserInfo> find();
    
    void resetPassword(String id, String username);
    
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
     * 修改密码
     * 
     * @param old 旧密码
     * @param newPassword 新密码
     * @return 
     * @see
     */
    int modifyPassword(String username, String old, String newPassword);
}
