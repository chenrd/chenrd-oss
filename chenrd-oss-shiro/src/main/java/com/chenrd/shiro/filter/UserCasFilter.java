/*
 * 文件名：UserCasFilter.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.filter;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cas.CasFilter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.spring.SpringBeanUtil;
import com.chenrd.sys.service.LogRecordService;
import com.chenrd.sys.service.UserService;
import com.chenrd.sys.service.info.LogInfo;
import com.chenrd.sys.service.info.MenuInfo;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月23日
 * @see UserCasFilter
 * @since
 */
public class UserCasFilter extends CasFilter
{
    /**
     * 
     */
    private UserService userService;
    
    /**
     * 
     */
    private String applyKey;
    
    /**
     * 
     */
    private Cache<String, UserInfo> userCache;
    
    /**
     * 
     */
    private Cache<String, String> subjects;
    
    /**
     * @param userCache
     */
    public UserCasFilter(CacheManager cacheManager)
    {
        this.userCache = cacheManager.getCache("userCache");
        this.subjects = cacheManager.getCache("login-subject");
    }
    
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
                                     ServletResponse response) throws Exception 
    {
        issueSuccessRedirect(request, response);
        String username = (String) token.getPrincipal();
        UserInfo user = userService.findPowerByUsername(username, applyKey);
        if(user == null)
        {
          //木有找到用户
            throw new UnknownAccountException("没有找到该账号");
        }
        /*
        * 交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以在此判断或自定义实现  
        */
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest rq = attr.getRequest();
        
        if (user.getMenusSet() != null && user.getMenusSet().size() > 0) combination(user.getMenusSet());
        
        HttpSession session = ((ShiroHttpServletRequest) request).getSession();
        session.setAttribute(UserInfo.OSS_SESSION_USER_ID, user.getId());
        session.setAttribute(UserInfo.OSS_SESSION_USER_NAME, user.getName());
        session.setAttribute(UserInfo.OSS_SESSION_USER_TYPE, user.getType());
        
        subjects.put(rq.getSession().getId(), session.getId());
        userCache.put(session.getId(), user);
        LogRecordService logRecordService = (LogRecordService) SpringBeanUtil.getObject("logRecordService");
        logRecordService.newLogRecord(new LogInfo(null, LogInfo.TYPE_LOGIN, new Date(), user.getUsername(), applyKey, "登陆成功"));
        return false;
    }
    
    /**
     * 从新组合集合->树枝
     * @param list 
     * @return ""
     * @see
     */
    private List<MenuInfo> combination(List<MenuInfo> list)
    {
        Map<String, MenuInfo> map = new LinkedHashMap<String, MenuInfo>();
        List<MenuInfo> infos = new ArrayList<MenuInfo>();
        for (MenuInfo info : list)
        {
            map.put(info.getKey(), info);
            if (info.getParentKey().equals(applyKey))
            {
                infos.add(info);
            }
        }
        
        MenuInfo info = null,  parent = null;
        for (String key : map.keySet()) 
        {
            info = map.get(key);
            if (!info.getParentKey().equals(applyKey))
            {
                parent = map.get(info.getParentKey());
                if (parent != null)
                {
                    parent.getChilds().add(info);
                }
            }
        }
        return infos;
    }

    /**
     * @return Returns the userService.
     */
    public UserService getUserService()
    {
        return userService;
    }

    /**
     * @param userService The userService to set.
     */
    public void setUserService(UserService userService)
    {
        this.userService = userService;
    }

    /**
     * @return Returns the applyKey.
     */
    public String getApplyKey()
    {
        return applyKey;
    }

    /**
     * @param applyKey The applyKey to set.
     */
    public void setApplyKey(String applyKey)
    {
        this.applyKey = applyKey;
    }
    
    
    
}
