/*
 * 文件名：LogController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenrd.common.FreemakerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.sys.business.LogRecordManager;

/**
 * @author chenrd
 * @version 2015年6月15日
 * @see LogController
 * @since
 */
@RequestMapping("log")
@Controller
public class LogController extends FreemakerController
{

    /**
     * 
     */
    @Resource(name = "logRecordService")
    private LogRecordManager logRecordService;
    
    /**
     * 
     * 返回列表页面
     * @return ""
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index()
    {
        return getViewName("view/log/index");
    }
    
    
    /**
     * 
     * 
     * @param applyKey 
     * @param type 
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "show/{applyKey}/{type}", method = RequestMethod.GET)
    public String show(@PathVariable String applyKey, @PathVariable int type, ModelMap map)
    {
        map.put("applyKey", applyKey);
        map.put("type", type);
        return getViewName("view/log/rows");
    }
    
    
    /**
     * 
     * @param applyKey 
     * @param type 
     * @param paging 
     * @return JQueryTableResult
     * @see
     */
    @RequestMapping(value = "find/{applyKey}/{type}", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult find(@PathVariable String applyKey, @PathVariable int type, Paging paging)
    {
        return new JQueryTableResult(logRecordService.findPaging(applyKey, type, paging), paging);
    }
    
    /**
     * 
     * @param id 
     * @return ""
     * @see
     */
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String show(@PathVariable Long id)
    {
        return logRecordService.getContent(id);
    }
    
}
