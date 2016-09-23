/*
 * 文件名：LogRecordService.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import com.chenrd.sys.service.info.LogInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月18日
 * @see LogRecordService
 * @since
 */
public interface LogRecordService
{
    
    /**
     * 
     * @param info 
     * @see
     */
    void newLogRecord(LogInfo info);
    
}
