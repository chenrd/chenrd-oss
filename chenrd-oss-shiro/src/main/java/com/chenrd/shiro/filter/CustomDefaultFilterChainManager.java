/*
 * 文件名：CustomDefaultFilterChainManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.filter;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;

import org.apache.shiro.config.Ini;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.filter.authc.AuthenticationFilter;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.apache.shiro.web.filter.mgt.SimpleNamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chenrd
 * @version 2015年6月17日
 * @see CustomDefaultFilterChainManager
 * @since
 */
public class CustomDefaultFilterChainManager extends DefaultFilterChainManager
{
    
    private static Logger LOG = LoggerFactory.getLogger(CustomDefaultFilterChainManager.class);
    /**
     * 过滤
     */
    private Map<String, String> filterChainDefinitionMap = null;

    /**
     * 登陆地址
     */
    private String loginUrl;
    
    /**
     * 登陆成功调整地址
     */
    private String successUrl;
    
    /**
     * 
     */
    private String unauthorizedUrl;
    
    /**
     * 
     */
    public CustomDefaultFilterChainManager() 
    {
        super();
    }

    /**
     * 
     * 
     * @param customFilters Map<String, Filter>
     * @see
     */
    public void setCustomFilters(Map<String, Filter> customFilters) 
    {
        for(Map.Entry<String, Filter> entry : customFilters.entrySet()) 
        {
            addFilter(entry.getKey(), entry.getValue(), false);
        }
    }
    
    /**
     * 
     * 
     * @param definitions String
     * @see
     */
    public void setDefaultFilterChainDefinitions(String definitions) 
    {
        LOG.warn("初始化权限配置：Ini ini = new Ini()");
        Ini ini = new Ini();
        ini.load(definitions);
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) 
        {
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
    }
    
    /**
     * 
     * @see
     */
    @PostConstruct
    public void init() 
    {
        Map<String, Filter> filters = getFilters();
        if (!CollectionUtils.isEmpty(filters)) 
        {
            for (Map.Entry<String, Filter> entry : filters.entrySet()) 
            {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                applyGlobalPropertiesIfNecessary(filter);
                if (filter instanceof Nameable) 
                {
                    ((Nameable) filter).setName(name);
                }
            }
        }

        //build up the chains:
        Map<String, String> chains = getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) 
        {
            for (Map.Entry<String, String> entry : chains.entrySet()) 
            {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                createChain(url, chainDefinition);
            }
        }
    }

    @Override
    protected void initFilter(Filter filter) 
    {
        //ignore
    }

    public FilterChain proxy(FilterChain original, List<String> chainNames) {
        NamedFilterList configured = new SimpleNamedFilterList(chainNames.toString());
        for(String chainName : chainNames) {
            configured.addAll(getChain(chainName));
        }
        return configured.proxy(original);
    }

    private void applyLoginUrlIfNecessary(Filter filter) {
        String loginUrl = getLoginUrl();
        if (StringUtils.hasText(loginUrl) && (filter instanceof AccessControlFilter)) {
            AccessControlFilter acFilter = (AccessControlFilter) filter;
            //only apply the login url if they haven't explicitly configured one already:
            String existingLoginUrl = acFilter.getLoginUrl();
            if (AccessControlFilter.DEFAULT_LOGIN_URL.equals(existingLoginUrl)) {
                acFilter.setLoginUrl(loginUrl);
            }
        }
    }

    private void applySuccessUrlIfNecessary(Filter filter) {
        String successUrl = getSuccessUrl();
        if (StringUtils.hasText(successUrl) && (filter instanceof AuthenticationFilter)) {
            AuthenticationFilter authcFilter = (AuthenticationFilter) filter;
            //only apply the successUrl if they haven't explicitly configured one already:
            String existingSuccessUrl = authcFilter.getSuccessUrl();
            if (AuthenticationFilter.DEFAULT_SUCCESS_URL.equals(existingSuccessUrl)) {
                authcFilter.setSuccessUrl(successUrl);
            }
        }
    }

    private void applyUnauthorizedUrlIfNecessary(Filter filter) {
        String unauthorizedUrl = getUnauthorizedUrl();
        if (StringUtils.hasText(unauthorizedUrl) && (filter instanceof AuthorizationFilter)) {
            AuthorizationFilter authzFilter = (AuthorizationFilter) filter;
            //only apply the unauthorizedUrl if they haven't explicitly configured one already:
            String existingUnauthorizedUrl = authzFilter.getUnauthorizedUrl();
            if (existingUnauthorizedUrl == null) {
                authzFilter.setUnauthorizedUrl(unauthorizedUrl);
            }
        }
    }

    private void applyGlobalPropertiesIfNecessary(Filter filter) {
        applyLoginUrlIfNecessary(filter);
        applySuccessUrlIfNecessary(filter);
        applyUnauthorizedUrlIfNecessary(filter);
    }

    /**
     * @return Returns the filterChainDefinitionMap.
     */
    public Map<String, String> getFilterChainDefinitionMap()
    {
        return filterChainDefinitionMap;
    }

    /**
     * @param filterChainDefinitionMap The filterChainDefinitionMap to set.
     */
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap)
    {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    /**
     * @return Returns the loginUrl.
     */
    public String getLoginUrl()
    {
        return loginUrl;
    }

    /**
     * @param loginUrl The loginUrl to set.
     */
    public void setLoginUrl(String loginUrl)
    {
        this.loginUrl = loginUrl;
    }

    /**
     * @return Returns the successUrl.
     */
    public String getSuccessUrl()
    {
        return successUrl;
    }

    /**
     * @param successUrl The successUrl to set.
     */
    public void setSuccessUrl(String successUrl)
    {
        this.successUrl = successUrl;
    }

    /**
     * @return Returns the unauthorizedUrl.
     */
    public String getUnauthorizedUrl()
    {
        return unauthorizedUrl;
    }

    /**
     * @param unauthorizedUrl The unauthorizedUrl to set.
     */
    public void setUnauthorizedUrl(String unauthorizedUrl)
    {
        this.unauthorizedUrl = unauthorizedUrl;
    }
    
}
