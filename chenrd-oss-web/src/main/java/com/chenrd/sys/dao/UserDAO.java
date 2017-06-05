/*
 * 文件名：UserDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.dao.BaseDAO;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserDAO
 * @since
 */
public interface UserDAO extends BaseDAO {
    
    /**
     * 用于判断是否用户名重复
     * 
     * @param username
     * @return 
     * @see
     */
    Long countByUserName(String username);
    
    /**
     * 查询拥有指定字段权限的用户集合，只返回id,username字段的值
     * @see
     */
    List<UserInfo> findFieldByPowerKey(String key);
    
}
