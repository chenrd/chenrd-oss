/*
 * 文件名：PropertiesBean.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月24日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
 * @author chenrd
 * @version 2015年6月24日
 * @see PropertiesBean
 * @since
 */
@Component("propertiesBean")
public class PropertiesBean
{
    /**
     * 
     */
    @Value("#{settings['apply.key']}")
    private String applyKey;
    
    /**
     * 
     */
    @Value("#{settings['apply.url']}")
    private String applyUrl;
    
    @Value("#{settings['oss.url']}")
    private String ossUrl;
    
    @Value("#{settings['cas.url']}")
    private String casUrl;
    
    /**
     * 
     */
    @Value("#{settings['default.password']}")
    private String defaultPassword;
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

    /**
     * @return Returns the applyUrl.
     */
    public String getApplyUrl()
    {
        return applyUrl;
    }

    /**
     * @param applyUrl The applyUrl to set.
     */
    public void setApplyUrl(String applyUrl)
    {
        this.applyUrl = applyUrl;
    }

    /**
     * @return Returns the defaultPassword.
     */
    public String getDefaultPassword()
    {
        return defaultPassword;
    }

    /**
     * @param defaultPassword The defaultPassword to set.
     */
    public void setDefaultPassword(String defaultPassword)
    {
        this.defaultPassword = defaultPassword;
    }

    /**
     * @return Returns the ossUrl.
     */
    public String getOssUrl()
    {
        return ossUrl;
    }

    /**
     * @param ossUrl The ossUrl to set.
     */
    public void setOssUrl(String ossUrl)
    {
        this.ossUrl = ossUrl;
    }

    /**
     * @return Returns the casUrl.
     */
    public String getCasUrl()
    {
        return casUrl;
    }

    /**
     * @param casUrl The casUrl to set.
     */
    public void setCasUrl(String casUrl)
    {
        this.casUrl = casUrl;
    }
    
}
