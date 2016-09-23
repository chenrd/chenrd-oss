/*
 * 文件名：PowerType.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

/**
 * 
 * 权限类型
 * @author chenrd
 * @version 2015年5月14日
 * @see PowerType
 * @since
 */
public interface PowerType
{
    /**
     * 菜单权限
     * 对应Power权限的type属性
     */
    int MENU_POWER = 0;
    
    /**
     * 功能权限
     * 对应Power权限的type属性
     */
    int FUNC_POWER = 1;

    /**
     * 字段类型控制权限
     * 对应Power权限的type属性
     */
    int FIELD_POWER = 2;
    
    
}
