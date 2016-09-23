/*
 * 文件名：SingleSignOutHttpSessionListener.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月24日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.jasig.cas.client.session.SessionMappingStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.spring.SpringBeanUtil;

/**
 * 
 * @author chenrd
 * @version 2015年6月24日
 * @see SingleSignOutHttpSessionListener
 * @since
 */
public final class SingleSignOutHttpSessionListener implements HttpSessionListener
{
    
    private SessionMappingStorage sessionMappingStorage;
    
    private static Logger LOG = LoggerFactory.getLogger(SingleSignOutHttpSessionListener.class);
    
    public void sessionCreated(final HttpSessionEvent event) {
        // nothing to do at the moment
        LOG.warn("satrt creating a session id={}", event.getSession().getId());
    }

    public void sessionDestroyed(final HttpSessionEvent event) {
        if (sessionMappingStorage == null) {
            sessionMappingStorage = getSessionMappingStorage();
        }
        final HttpSession session = event.getSession();
        UserEhcacheHandle userEhcacheHandle = (UserEhcacheHandle) SpringBeanUtil.getObject("userEhcacheHandle");
        userEhcacheHandle.removeSessionId(session.getId());
        sessionMappingStorage.removeBySessionById(session.getId());
    }

    /**
     * Obtains a {@link SessionMappingStorage} object. Assumes this method will always return the same
     * instance of the object.  It assumes this because it generally lazily calls the method.
     * 
     * @return the SessionMappingStorage
     */
    protected static SessionMappingStorage getSessionMappingStorage() {
        return SingleSignOutFilter.getSingleSignOutHandler().getSessionMappingStorage();
    }
}
