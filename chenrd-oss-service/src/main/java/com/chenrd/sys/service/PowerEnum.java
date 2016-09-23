/*
 * 文件名：PowerType.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

/**
 * 
 * 权限类别定义
 * @author chenrd
 * @version 2015年6月4日
 * @see PowerEnum
 * @since
 */
public enum PowerEnum 
{
    /**
     * 菜单权限，功能权限，字段权限
     */
    MENU(100), FUNC(200), FILED(300);
    
    /**
     * 
     */
    private int key;
    
    /**
     * 
     */
    PowerEnum(int key)
    {
        this.key = key;
    }

    /**
     * @return Returns the key.
     */
    public int getKey()
    {
        return key;
    }
}
