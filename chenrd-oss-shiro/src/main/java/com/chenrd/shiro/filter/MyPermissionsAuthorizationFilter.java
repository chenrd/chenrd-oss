/*
 * 文件名：MyPermissionsAuthorizationFilter.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2015年12月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import com.chenrd.shiro.AuthorRuntimeException;

/**
 * 最最重要的一点，解决了页面没有刷新点击功能，但是后台的author已经被注销的情况下会去发送cas请求而产生的跨域问题
 * 
 * @author chenrd
 * @version 2015年12月11日
 * @see MyPermissionsAuthorizationFilter
 * @since
 */
public class MyPermissionsAuthorizationFilter extends PermissionsAuthorizationFilter
{
    
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        System.out.println(((HttpServletRequest) request).getServletPath());
        throw new AuthorRuntimeException("身份异常，不进行转发到登录页面");
    }
}
