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

import com.chenrd.common.Paging;
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
public interface PowerDAO extends BaseDAO
{
    /**
     * 
     * @param applyId 应用ID
     * @param parentKey 父几点KEY
     * @param type ""
     * @param paging 分页条件
     * @return ""
     * @see
     */
    List<Power> findPaging(Long applyId, String parentKey, int type, Paging paging);
    
    /**
     * 获取全部的类型权限
     * @param type 
     * @return List<Power>
     * @see
     */
    List<Power> findAll(int type);
    
    /**
     * 获取功能及属性权限Status=Status.NO
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Power>
     * @see
     */
    List<Power> findNotMenuByPower(String username, String applyKey);
    
    /**
     * 功能
     * 
     * @param username
     * @return 
     * @see
     */
    List<Power> findRoleFuncByUsername(String username);
    
    /**
     * 功能
     * 
     * @param username
     * @return 
     * @see
     */
    List<Power> findUserFuncByUsername(String username);
    
    /**
     * 获取功能及属性权限Status=Status.NO
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Power>
     * @see
     */
    List<Power> findNotMenuByRole(String username, String applyKey);
    
    /**
     * 
     * @param applyKey 
     * @return List<Power>
     * @see
     */
    List<Power> findFilter(String applyKey);
    
    /**
     * 
     * @param power 
     * @see
     */
    void addPower(Attribute power);
    
    /**
     * 
     * @param applyKey
     * @param username
     * @param powerType
     * @return 
     * @see
     */
    List<Power> findByApplyKeyUsername(String applyKey, String username, int powerType);
    
}
