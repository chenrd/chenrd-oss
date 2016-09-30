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

import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.sys.entity.User;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserDAO
 * @since
 */
public interface UserDAO extends BaseDAO
{
    
    /**
     * 
     * 分页查询数据
     * @param name 姓名
     * @param phone 手机
     * @param paging 分页
     * @return List<UserInfo>
     * @see
     */
    List<User> findPaging(String name, String phone, String username, Paging paging);
    
    /**
     * 
     * 逻辑删除
     * @param id 用户ID
     * @see
     */
    void logicDelete(String id);
    
    /**
     * 用于判断是否用户名重复
     * 
     * @param username
     * @return 
     * @see
     */
    Long countByUserName(String username);
    
}
