/*
 * 文件名：UserPowerKey.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年9月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity.classKey;

import java.io.Serializable;


public class UserPowerKey implements Serializable
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 7882770067433167241L;
    
    private String userId;
    
    private Long powerId;

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int PRIME = 31;  
        int result = 1;  
        result = PRIME * result + (userId.hashCode());
        result = PRIME * result + powerId.hashCode();
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if(this == obj) return true;  
        if(obj == null) return false;  
        if(!(obj instanceof UserPowerKey)) return false;  
        UserPowerKey objKey = (UserPowerKey) obj;  
        if(powerId == objKey.powerId && userId.equalsIgnoreCase(objKey.userId)) {  
            return true;  
        }
        return false;  
    }

    /**
     * 
     */
    public UserPowerKey()
    {
        super();
    }

    /**
     * @param userId
     * @param powerId
     */
    public UserPowerKey(String userId, Long powerId)
    {
        super();
        this.userId = userId;
        this.powerId = powerId;
    }

    /**
     * @return Returns the userId.
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    /**
     * @return Returns the powerId.
     */
    public Long getPowerId()
    {
        return powerId;
    }

    /**
     * @param powerId The powerId to set.
     */
    public void setPowerId(Long powerId)
    {
        this.powerId = powerId;
    }

    
}
