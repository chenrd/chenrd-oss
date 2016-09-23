/*
 * 文件名：MyExceptionResolver.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.ex;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.chenrd.common.BaseExecuteException;
import com.chenrd.common.ControllerResult;
import com.chenrd.common.RegularExpressionUtil;
import com.chenrd.shiro.AuthorRuntimeException;
import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.service.LogRecordService;
import com.chenrd.sys.service.info.LogInfo;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 异常处理
 * @author chenrd
 * @version 2015年5月20日
 * @see MyExceptionResolver
 * @since
 */
@Component
public class MyExceptionResolver implements HandlerExceptionResolver
{
    
    /**
     * 
     */
    @Value("#{settings['apply.key']}")
    private String applyKey;
    
    /**
     * 
     */
    @Resource(name = "userEhcacheHandle")
    private UserEhcacheHandle userEhcacheHandle;
    
    /**
     * 
     */
    @Resource(name = "logRecordService")
    private LogRecordService logRecordService;
    
    /**
     * 
     */
    private Logger log = LoggerFactory.getLogger(MyExceptionResolver.class);
    
    /**
     * 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler 
     * @param ex 
     * @return ModelAndView
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex)
    {
        
        Map<String, PowerInfo> params = userEhcacheHandle.get(request.getRequestedSessionId()).getPowers();
        String lable = "";
        for (String key : params.keySet())
        {
            if (RegularExpressionUtil.matches(key, request.getServletPath()))
            {
                lable = params.get(key).getFullName();
                break;
            }
        }
        logRecordService.newLogRecord(new LogInfo(null, LogInfo.TYPE_ERROR, new Date(), (String) request.getSession().getAttribute(UserInfo.OSS_SESSION_USER_NAME), applyKey, lable, exceptionContent(ex)));
        if (!(ex instanceof BaseExecuteException))
        {
            log.error("系统错误：", ex);
        }
        if (ex instanceof AuthorRuntimeException)
        {
            response.setStatus(500);
        }
        String accept = request.getHeader("Accept");
        if (accept.indexOf("text/html") != -1)
        {
            return new ModelAndView("/common/html/error-500");
        }
        else
        {
            ModelMap map = new ModelMap();
            map.put(ControllerResult.STATUSCODE, ControllerResult.ERRORCODE);
            map.put(ControllerResult.CENTENT_LABLE, ex.getMessage());
            return new ModelAndView("/common/html/errorjson", map);
        }
        
    }
    
    /**
     * 
     * 
     * @param ex Exception
     * @return ""
     * @see
     */
    private String exceptionContent(Exception ex) 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getClass().getSimpleName()).append("->").append(ex.getMessage());
        for (StackTraceElement element : ex.getStackTrace()) 
        {
            builder.append("\r\n").append(element.getClassName()).append(element.getMethodName())
            .append("[").append(element.getFileName()).append(":").append(element.getLineNumber()).append("]");
        }
        return builder.toString();
    }
    
}
