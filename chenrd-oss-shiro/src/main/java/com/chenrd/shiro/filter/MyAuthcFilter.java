/*
 * 文件名：MyAuthcFilter.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chenrd
 * @version 2015年6月19日
 * @see MyAuthcFilter
 * @since
 */
public class MyAuthcFilter extends FormAuthenticationFilter
{
    
    /**
     * 
     */
    private static Logger LOG = LoggerFactory.getLogger(MyAuthcFilter.class);
    
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception 
    {
        if (isLoginRequest(request, response)) 
        {
            if (isLoginSubmission(request, response)) 
            {
                if (LOG.isTraceEnabled()) 
                {
                    LOG.trace("登录提交检测。尝试登录。");
                }
                return executeLogin(request, response);
            } 
            else 
            {
                if (LOG.isTraceEnabled()) 
                {
                    LOG.trace("登录页面视图");
                }
                //allow them to see the login page ;)
                return true;
            }
        } 
        else 
        {
            
            if (LOG.isTraceEnabled()) 
            {
                LOG.trace("试图访问路径需要认证。转发到 " +
                        "认证的URL [" + getLoginUrl() + "]");
            }

            saveRequestAndRedirectToLogin(request, response);
            return false;
        }
    }
}
