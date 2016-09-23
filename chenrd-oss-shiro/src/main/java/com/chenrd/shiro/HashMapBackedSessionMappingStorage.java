/*
 * 文件名：HashMapBackedSessionMappingStorage.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年2月23日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.cas.client.session.SessionMappingStorage;

/**
 * 解决统一注销失败
 * request对象是否正确（解决统一注销失败：多出一个回调/cas）
 * @author chenrd
 * @version 2016年2月23日
 * @see HashMapBackedSessionMappingStorage
 * @since
 */
public class HashMapBackedSessionMappingStorage implements SessionMappingStorage
{
    /**
     * Maps the ID from the CAS server to the Session.
     */
    private final Map<String,HttpSession> MANAGED_SESSIONS = new HashMap<String,HttpSession>();

    /**
     * Maps the Session ID to the key from the CAS Server.
     */
    private final Map<String,String> ID_TO_SESSION_KEY_MAPPING = new HashMap<String,String>();

    private final Log log = LogFactory.getLog(getClass());

    public synchronized void addSessionById(String mappingId, HttpSession session) {
        ID_TO_SESSION_KEY_MAPPING.put(session.getId(), mappingId);
        MANAGED_SESSIONS.put(mappingId, session);

    }                               

    public synchronized void removeBySessionById(String sessionId) {
        if (log.isDebugEnabled()) {
            log.debug("Attempting to remove Session=[" + sessionId + "]");
        }

        final String key = ID_TO_SESSION_KEY_MAPPING.get(sessionId);

        if (log.isDebugEnabled()) {
            if (key != null) {
                log.debug("Found mapping for session.  Session Removed.");
            } else {
                log.debug("No mapping for session found.  Ignoring.");
            }
        }
        MANAGED_SESSIONS.remove(key);
        ID_TO_SESSION_KEY_MAPPING.remove(sessionId);
    }

    public synchronized HttpSession removeSessionByMappingId(String mappingId) {
        final HttpSession session = MANAGED_SESSIONS.get(mappingId);

        if (session != null) {
            removeBySessionById(session.getId());
        }

        return session;
    }
    
    public HttpSession getSessionId(String mappingId)
    {
        return (HttpSession) MANAGED_SESSIONS.get(mappingId);
    }
}
