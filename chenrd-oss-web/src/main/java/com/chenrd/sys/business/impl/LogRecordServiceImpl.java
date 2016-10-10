/*
 * 文件名：LogRecordServiceImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.dao.BeanUtil;
import com.chenrd.sys.business.LogRecordManager;
import com.chenrd.sys.entity.LogRecord;
import com.chenrd.sys.service.LogRecordService;
import com.chenrd.sys.service.info.LogInfo;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see LogRecordServiceImpl
 * @since
 */
@Transactional
@Service("logRecordService")
public class LogRecordServiceImpl implements LogRecordManager, LogRecordService
{

    /**
     * 
     */
    @Resource(name = "logRevordDAO")
    private BaseDAO logRevordDAO;
    
    /*
     * (non-Javadoc)
     * @see com.chenrd.ex.service.LogRecordService#findPaging(com.chenrd.common.Paging)
     */
    @Override
    public List<LogInfo> findPaging(String applyKey, int type, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        params.put("applyKey", applyKey);
        params.put("type", type);
        return logRevordDAO.findQueryNamePaging("logList", params, paging);
    }

    @Override
    public String getContent(Long id)
    {
        return logRevordDAO.get(LogRecord.class, id).getContent();
    }

    @Override
    public void newLogRecord(LogInfo info)
    {
        LogRecord record = new LogRecord();
        BeanUtil.copyProperties(info, record);
        record.setCreateDate(new Date());
        logRevordDAO.saveOrUpdate(record);
    }

}
