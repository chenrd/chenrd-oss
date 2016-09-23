/*
 * 文件名：PowerService.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月27日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.List;

import com.chenrd.sys.service.info.PowerInfo;

/**
 * 
 * 权限接口
 * @author chenrd
 * @version 2015年5月27日
 * @see PowerService
 * @since
 */
public interface PowerService
{

    /**
     * 
     * 添加一个字段类型的权限
     * @param info PowerInfo
     * @see
     */
    void addFieldPower(PowerInfo info);
    
    /**
     * 
     * @param applyKey 应用KEY
     * @return ""
     * @see
     */
    List<PowerInfo> findShiroFiler(String applyKey);
    
    /**
     * 通过URL查询当前请求是对应哪一个功能应用
     * 
     * @param url
     * @return 
     * @see
     */
    String queryFullNameByUrl(String url);
    
    /**
     * 通过用户名及应用KEY查询权限
     * 
     * @param applyKey 应用KEY 不能为空
     * @param username 用户名 不能为空
     * @param powerType 类型 
     * @return 
     * @see
     */
    List<PowerInfo> findByApplyKeyUsername(String applyKey, String username, int powerType);
    
}
