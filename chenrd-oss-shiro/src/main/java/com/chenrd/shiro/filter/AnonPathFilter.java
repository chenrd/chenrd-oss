/*
 * 文件名：AnonPathFilter.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.PathMatchingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chenrd
 * @version 2015年6月17日
 * @see AnonPathFilter
 * @since
 */
public class AnonPathFilter extends PathMatchingFilter
{
    /**
     * 
     */
    private static Logger LOG = LoggerFactory.getLogger(AnonPathFilter.class);
    
    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
    {
        LOG.warn("进入级别为:anon的过滤器");
        return true;
    }

}
