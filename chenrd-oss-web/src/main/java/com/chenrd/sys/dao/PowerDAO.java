/*
 * 文件名：PowerDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月27日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.dao.BaseDAO;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Power;

/**
 * 
 * @author chenrd
 * @version 2015年5月27日
 * @see PowerDAO
 * @since
 */
public interface PowerDAO extends BaseDAO {
    
    /**
     * 获取功能及属性权限Status=Status.NO
     * 用户功能权限
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Power>
     * @see
     */
    List<Power> findUserFuncPower(String username, String applyKey);
    
    /**
     * 获取功能及属性权限Status=Status.NO
     * 角色功能权限
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Power>
     * @see
     */
    List<Power> findRoleFuncPower(String username, String applyKey);
    
    /**
     * 获取用户的字段权限
     * 
     * @param username
     * @param applyKey
     * @return 
     * @see
     */
    List<Power> findUserFieldPower(String username, String applyKey);
    
    /**
     * 普通用户分配自己的功能权限
     * 
     * @param username
     * @return 
     * @see
     */
    List<Power> findRoleFuncByUsername(String username);
    
    /**
     * 普通用户分配自己的功能权限
     * 
     * @param username
     * @return 
     * @see
     */
    List<Power> findUserFuncByUsername(String username);
    
    
    /**
     * 获取用户的所有的字段权限
     * 
     * @param username
     * @param applyKey
     * @return 
     * @see
     */
    List<Attribute> findUserFieldPower(String username);
    
    
    /**
     * 
     * @param applyKey 
     * @return List<Power>
     * @see
     */
    List<Power> findFilter(String applyKey);
    
    /**
     * 
     * @param applyKey
     * @param username
     * @param powerType
     * @return 
     * @see
     */
    List<Power> findByApplyKeyUsername(String applyKey, String username, int powerType);
    
    /**
     * 
     * @param power 
     * @see
     */
    void addPower(Attribute power);
    
}
