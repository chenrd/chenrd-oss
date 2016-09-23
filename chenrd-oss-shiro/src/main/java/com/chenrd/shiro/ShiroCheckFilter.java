/*
 * 文件名：ShiroCheckFilter.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.spring.SpringBeanUtil;
import com.chenrd.sys.service.LogRecordService;
import com.chenrd.sys.service.info.LogInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月30日
 * @see ShiroCheckFilter
 * @since
 */
public class ShiroCheckFilter implements Filter
{
    
    @Override
    public void init(FilterConfig filterConfig)
        throws ServletException
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        UserEhcacheHandle userEhcacheHandle = (UserEhcacheHandle) SpringBeanUtil.getObject("userEhcacheHandle");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest rq = attr.getRequest();
        if (!userEhcacheHandle.isShiroCheck(rq.getSession().getId()))
        {
            Subject user = SecurityUtils.getSubject();
            if (StringUtils.isNotBlank((String) user.getPrincipal()))
            {
                LogRecordService logRecordService = (LogRecordService) SpringBeanUtil.getObject("logRecordService");
                PropertiesBean propertiesBean = (PropertiesBean) SpringBeanUtil.getObject("propertiesBean");
                logRecordService.newLogRecord(new LogInfo(null, LogInfo.TYPE_LOGIN, new Date(), (String) user.getPrincipal(), propertiesBean.getApplyKey(), "注销成功"));
                userEhcacheHandle.remove(((ShiroHttpServletRequest) request).getRequestedSessionId());
                user.logout();
                ((HttpServletResponse) response).sendRedirect(propertiesBean.getCasUrl() + "/login?service=" + propertiesBean.getApplyUrl() + "/cas");
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
        
    }

}
