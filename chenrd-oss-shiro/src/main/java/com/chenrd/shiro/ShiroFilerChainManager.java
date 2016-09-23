/*
 * 文件名：ShiroFilerChainManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;

import com.chenrd.sys.service.PowerService;
import com.chenrd.sys.service.info.PowerInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月16日
 * @see ShiroFilerChainManager
 * @since
 */
public class ShiroFilerChainManager
{
    /**
     * 
     */
    private DefaultFilterChainManager filterChainManager;
    
    /**
     * 
     */
    private PowerService powerService;
    
    /**
     * 
     */
    private String applyKey;
    
    /**
     * 
     */
    private Map<String, NamedFilterList> filterChains = new LinkedHashMap<String, NamedFilterList>();
    
    /**
     * 
     * @see
     */
    public void init() 
    {
        filterChains.putAll(filterChainManager.getFilterChains());
        filterChainManager.getFilterChains().clear();
        //2、循环URL Filter 注册filter chain  
        List<PowerInfo> powers = powerService.findShiroFiler(applyKey);
        if (powers != null) 
        {
            for (PowerInfo info : powers) 
            {
                if (!StringUtils.isNotBlank(info.getUrl()))
                {
                    continue;
                }
                filterChainManager.addToChain(info.getUrl(), "perms", info.getKey());
            }
        }
        filterChainManager.getFilterChains().putAll(filterChains);
        filterChains = null;
    }

    /**
     * @param filterChainManager The filterChainManager to set.
     */
    public void setFilterChainManager(DefaultFilterChainManager filterChainManager)
    {
        this.filterChainManager = filterChainManager;
    }

    /**
     * @param powerService The powerService to set.
     */
    public void setPowerService(PowerService powerService)
    {
        this.powerService = powerService;
    }

    /**
     * @param applyKey The applyKey to set.
     */
    public void setApplyKey(String applyKey)
    {
        this.applyKey = applyKey;
    }
    
    
    
    
}
