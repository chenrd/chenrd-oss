/*
 * 文件名：IndexController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.common.FreemakerController;
import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.service.LogRecordService;
import com.chenrd.sys.service.UserService;
import com.chenrd.sys.service.info.LogInfo;
import com.chenrd.sys.service.info.MenuInfo;
import com.chenrd.sys.service.info.UserInfo;
/**
 * 
 * @author chenrd
 * @version 2015年5月15日
 * @see IndexController
 * @since
 */
@Controller
public class IndexController extends FreemakerController
{
    
    /**
     * 
     */
    @Resource(name = "userEhcacheHandle")
    private UserEhcacheHandle userEhcacheHandle;
    
    /**
     * 应用KEY
     */
    @Value("#{settings['apply.key']}")
    private String applyKey;
    
    /**
     * 
     */
    @Resource(name = "logRecordService")
    private LogRecordService logRecordService;
    
    /**
     * 
     */
    @Value("#{settings['cas.url']}")
    private String casUrl;
    
    
    /**
     * 
     */
    @Value("#{settings['apply.url']}")
    private String applyUrl;
    
    @Resource
    private UserService userService;
    
    /**
     * 
     * 首页
     * @param request 
     * @param map 
     * @return 首页
     * @see
     */
    @RequestMapping("")
    public String index(HttpServletRequest request, ModelMap map)
    {
        UserInfo userInfo = userEhcacheHandle.get(request.getRequestedSessionId());
        List<MenuInfo> infos = new ArrayList<MenuInfo>();
        for (MenuInfo info : userInfo.getMenusSet())
        {
            if (info.getParentKey().equals(applyKey))
            {
                infos.add(info);
            }
        }
        map.put("applys", userInfo.getApplys());
        map.put("casUrl", casUrl);
        map.put("applyUrl", applyUrl);
        map.put("menus", infos);
        return getViewName("index");
    }
    
    /**
     * 
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public void updatePassword(String oldPassword, String newPassword, HttpServletRequest request)
    {
        int status = userService.modifyPassword(request.getUserPrincipal().getName(), oldPassword, newPassword);
        if (status == 1)
        {
            throw new RuntimeException("修改密码失败，用户【" + request.getUserPrincipal().getName() + "】不存在");
        }
        else if (status == 2)
        {
            throw new RuntimeException("旧密码错误");
        }
    }
    
    /**
     * 
     * 无权限访问
     * @return ""
     * @see
     */
    @RequestMapping(value = "authority")
    public String authority()
    {
        return "common/html/error-500";
    }
    
    /**
     * 
     * @param request 
     * @see
     */
    @RequestMapping(value = "logout")
    @ResponseBody
    public void logout(HttpServletRequest request)
    {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest rq = attr.getRequest();
        userEhcacheHandle.remove(rq.getSession().getId());
        request.getSession().removeAttribute(UserInfo.OSS_SESSION_USER_ID);
        request.getSession().removeAttribute(UserInfo.OSS_SESSION_USER_NAME);
        Subject user = SecurityUtils.getSubject();
        LogInfo logInfo = new LogInfo(null, LogInfo.TYPE_LOGIN, null, request.getUserPrincipal().getName(),
            applyKey, "注销用户");
        logRecordService.newLogRecord(logInfo);
        user.logout();
    }
}
