/*
 * 文件名：MenuDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.vo.MenuVO;

/**
 * 菜单权限DAO
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuDAO
 * @since
 */
public interface MenuDAO extends BaseDAO
{
    /**
     * 
     * @param applyId 
     * @param parentKey 
     * @param paging 分页条件
     * @return List<MenuVO>
     * @see
     */
    List<Menu> findChilds(Long applyId, String parentKey, Paging paging);
    
    /**
     * 
     * @param username 用户名
     * @param applyKey 应用
     * @return 菜单
     * @see
     */
    List<Menu> findRolePowerByUsername(String username, String applyKey);
    
    
    /**
     * 
     * @param username 用户名
     * @param applyKey 应用
     * @return 菜单
     * @see
     */
    List<Menu> findRolePowerByUsername(String username);
    
    /**
     * 
     * 
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Menu>
     * @see
     */
    List<Menu> findUserPowerByUsername(String username, String applyKey);
    
    /**
     * 
     * 
     * @param username 用户名
     * @param applyKey 应用KEY
     * @return List<Menu>
     * @see
     */
    List<Menu> findUserPowerByUsername(String username);
    
    /**
     * 
     * 
     * @return List<MenuVO>
     * @see
     */
    List<MenuVO> findAll();
    
    /**
     * 
     * 通过KEY获取目录
     * @param key 目录KEY
     * @return Menu
     * @see
     */
    Menu getByKey(String key);
    
    /**
     * 
     * 通过路径获取全称
     * @param url 
     * @return 全称
     * @see
     */
    List<String> queryFullNameByUrl(String url);
}
