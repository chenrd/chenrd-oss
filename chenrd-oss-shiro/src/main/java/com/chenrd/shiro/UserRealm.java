/*
 * 文件名：UserRealm.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cas.CasRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.service.info.MenuInfo;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.service.info.RoleInfo;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月14日
 * @see UserRealm
 * @since
 */
public class UserRealm extends CasRealm
{
    private UserEhcacheHandle userEhcacheHandle;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals)
    {
        
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest rq = attr.getRequest();
        
        String shiroSessionId = userEhcacheHandle.getShiroSessionId(rq.getSession().getId());
        
        UserInfo info = userEhcacheHandle.get(shiroSessionId);
        //角色名的集合
        Set<String> roles = new HashSet<String>();
        //权限名的集合
        Set<String> permissions = new HashSet<String>();
        //角色权限
        if (info.getRoleSet() != null) 
        {
            for (RoleInfo role : info.getRoleSet())
            {
                roles.add(role.getKey());
            }
        }
        //菜单权限
        if (info.getMenusSet() != null)
        {
            for (MenuInfo menuInfo : info.getMenusSet())
            {
                permissions.add(menuInfo.getKey());
            }
        }
        //功能权限
        if (info.getPowers() != null)
        {
            for (String key : info.getPowers().keySet())
            {
                permissions.add(((PowerInfo) info.getPowers().get(key)).getKey());
            }
        }
        
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * @param userEhcacheHandle The userEhcacheHandle to set.
     */
    public void setUserEhcacheHandle(UserEhcacheHandle userEhcacheHandle)
    {
        this.userEhcacheHandle = userEhcacheHandle;
    }
}
