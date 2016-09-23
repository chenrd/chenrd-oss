/*
 * 文件名：EhcacheHandle.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.ehcache;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * 缓存处理实例
 * @author chenrd
 * @version 2015年5月14日
 * @see UserEhcacheHandle
 * @since
 */
public class UserEhcacheHandle 
{
    /**
     * 
     */
    private Cache<String, UserInfo> userCache = null;
    
    /**
     * 
     */
    private Cache<String, String> subjects;
    
    /**
     * 
     * 往用户缓存中添加一个用户
     * @param userInfo 
     * @see
     */
    public void put(UserInfo userInfo) 
    {
        userCache.put(userInfo.getUsername(), userInfo);
    }
    
    /**
     * 获取一个用户信息
     * @param id 用户id
     * @return UserInfo
     */
    public UserInfo get(String id) 
    {
        return userCache.get(id);
    }
    
    /**
     * 
     * @param sessionId 
     * @return true or false
     * @see
     */
    public boolean isShiroCheck(String sessionId)
    {
        return StringUtils.isNotBlank(subjects.get(sessionId));
    }
    
    /**
     * 
     * @param sessionId 
     * @param subject  
     * @see
     */
    public void putSubject(String sessionId, String shiroSessionId)
    {
        subjects.put(sessionId, shiroSessionId);
    }
    
    public String getShiroSessionId(String sessionId)
    {
        return subjects.get(sessionId);
    }
    
    /**
     * 
     * 
     * @param sessionId 
     * @see
     */
    public void removeSessionId(String sessionId)
    {
        subjects.remove(sessionId);
    }
    
    /**
     * 
     * 删除一个
     * @param id ""
     * @see
     */
    public void remove(String id)
    {
        userCache.remove(id);
    }



    /**
     * @param userCache
     */
    public UserEhcacheHandle(CacheManager cacheManager)
    {
        this.userCache = cacheManager.getCache("userCache");
        this.subjects = cacheManager.getCache("login-subject");
    }

    /**
     * 
     */
    public UserEhcacheHandle()
    {
        super();
    }
    
    
}
