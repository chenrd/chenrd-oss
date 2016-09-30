/*
 * 文件名：UserPowerMapping.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年9月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.chenrd.example.Domain;
import com.chenrd.sys.entity.classKey.UserPowerKey;

/**
 * 
 * @author chenrd
 * @version 2016年9月30日
 * @see UserPowerMapping
 * @since
 */
@Entity
@Table(name = "OSS_USER_POWER")
@IdClass(UserPowerKey.class)
public class UserPowerMapping extends Domain
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -7658540332271147730L;
    
    @Id
    @Column(name = "USER_ID")
    private String userId;
    
    @Id
    @Column(name = "POWER_ID")
    private Long powerId;

    /**
     * 
     */
    public UserPowerMapping()
    {
        super();
    }

    /**
     * @param userId
     * @param powerId
     */
    public UserPowerMapping(String userId, Long powerId)
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
