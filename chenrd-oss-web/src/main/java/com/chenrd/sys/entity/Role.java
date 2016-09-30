/*
 * 文件名：Role.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.chenrd.dao.QueryByUsername;
import com.chenrd.example.Domain;
import com.chenrd.oss.power.ann.LimitClassPower;
import com.chenrd.oss.power.ann.LimitFieldPower;

/**
 * 角色
 * @author chenrd
 * @version 2015年5月20日
 * @see Role
 * @since
 */
@LimitClassPower
@Entity
@Table(name = "OSS_ROLE")
@QueryByUsername
public class Role extends Domain
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -7169720624265457466L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    /**
     * 
     */
    @Column(name = "NAME_", length = 50)
    private String name;

    /**
     * 
     */
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "OSS_ROLE_POWER", joinColumns = {@JoinColumn(name= "ROLE_ID")}, inverseJoinColumns = {@JoinColumn(name = "POWER_ID")})
    private Set<Power> powers;
    
    /**
     * 
     */
    @Column(name = "KEY_", length = 100)
    private String key;
    
    /**
     * 
     */
    @Column(name = "REMARK", length = 200)
    private String remark;
    
    /**
     * 
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;
    
    /**
     * 创建者
     */
    @LimitFieldPower({"100", "100"})
    @Column(name = "CREATE_USER", length = 50, nullable = false)
    private String createUser;
    
    @Column(name = "UPDATE_DATE")
    private Date updateDate;
    
    @Column(name = "UPDATE_USER", length = 50, nullable = false)
    private String updateUser;
    /**
     * 
     */
    @Column(name = "STATUS")
    private int status;

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the powers.
     */
    public Set<Power> getPowers()
    {
        return powers;
    }

    /**
     * @param powers The powers to set.
     */
    public void setPowers(Set<Power> powers)
    {
        this.powers = powers;
    }

    /**
     * @return Returns the key.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key The key to set.
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return Returns the remark.
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark The remark to set.
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * @return Returns the createDate.
     */
    public Date getCreateDate()
    {
        return createDate;
    }

    /**
     * @param createDate The createDate to set.
     */
    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the status.
     */
    public int getStatus()
    {
        return status;
    }

    /**
     * @param status The status to set.
     */
    public void setStatus(int status)
    {
        this.status = status;
    }

    /**
     * @return Returns the createUser.
     */
    public String getCreateUser()
    {
        return createUser;
    }

    /**
     * @param createUser The createUser to set.
     */
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    /**
     * @return Returns the updateDate.
     */
    public Date getUpdateDate()
    {
        return updateDate;
    }

    /**
     * @param updateDate The updateDate to set.
     */
    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    /**
     * @return Returns the updateUser.
     */
    public String getUpdateUser()
    {
        return updateUser;
    }

    /**
     * @param updateUser The updateUser to set.
     */
    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }
    
    
}
