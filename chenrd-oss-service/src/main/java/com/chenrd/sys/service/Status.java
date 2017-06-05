/*
 * 文件名：Status.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

/**
 * 
 * @author chenrd
 * @version 2015年6月15日
 * @see Status
 * @since
 */
public interface Status
{
    /**
     * 删除状态
     */
    int DELETED = -1;
    
    /**
     * 不启用状态
     */
    int OFF = 0;
    
    /**
     * 启用
     */
    int ON = 1;
    
}
