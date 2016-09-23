/*
 * 文件名：User.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.example.Domain;
import com.chenrd.oss.power.ann.DefClassPower;
import com.chenrd.oss.power.ann.DefFieldPower;


/**
 * 用户
 * @author chenrd
 * @version 2015年5月12日
 * @see User
 * @since
 */
@DefClassPower(value = "用户")
@Table(name = "OSS_USER")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "CATEGORY")
public class User extends Domain
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -3835274038224964914L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "UUID", length = 32)
    private String id;
    
    /**
     * 姓名
     */
    @Column(name = "NAME_", length = 50)
    private String name;
    
    /**
     * 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OSS_USER_ORGANIZATION", joinColumns = {@JoinColumn(name= "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ORGANIZATION_ID")})
    private Set<Organization> organization;
    
    /**
     * 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OSS_USER_ROLE", joinColumns = {@JoinColumn(name= "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles;
    
    /**
     * 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OSS_USER_POWER", joinColumns = {@JoinColumn(name= "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "POWER_ID")})
    private Set<Power> powers;
    
    /**
     * 曾用名
     */
    @Column(name = "HISTORY_NAME", length = 50)
    private String historyName;
    
    /**
     * 系统登录名
     */
    @QueryOrder(value = "asc", index = 2)
    @QueryParams
    @DefFieldPower("创建用户")
    @Column(name = "USER_NAME", length = 30, unique = true)
    private String username;
    
    /**
     * 密码
     */
    @Column(name = "PASSWORD_", length = 50)
    private String password;
    
    /**
     * 性别
     */
    @Column(name = "SEX_", length = 1)
    private int sex;
    
    /**
     * 1: 管理员
     * 2: 普通账户
     */
    @Column(name = "TYPE", length = 1, nullable = false)
    private int type = 2;
    
    /**
     * 手机
     */
    @Column(name = "PHONE_", length = 20)
    private String phone;
    
    /**
     * 电话
     */
    @QueryParams
    @Column(name = "TELEPHONE", length = 20)
    private String telephone;
    
    /**
     * 邮箱
     */
    @Column(name = "EMAIL", length = 50)
    private String email;
    
    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate = new Date();
    
    /**
     * 
     */
    @Column(name = "CREATE_USER", length = 50)
    private String createUser;
    
    /**
     * 
     */
    @Column(name = "UPDATE_DATE")
    private Date updateDate = new Date();
    
    /**
     * 
     */
    @Column(name = "UPDATE_USER", length = 50)
    private String updateUser;
    
    /**
     * 肖像
     */
    @Column(name = "ICON_")
    private String icon;
    
    /**
     * 来源
     */
    @Column(name = "SOURCE", length = 100)
    private String source;
    
    /**
     * 
     */
    @QueryOrder(value = "desc", index = 1)
    @QueryParams(nexus = Nexus.GTEQUAL)
    @Column(name = "STATUS")
    private int status;
    
    /**
     * 
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "OSS_USER_APPLY", joinColumns = {@JoinColumn(name= "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "APPLY_ID")})
    private Set<Apply> applys;
    
    /**
     * 从哪个系统添加过来的
     */
    @Column(name = "APPLY_KEY", length = 50)
    private String applyKey;
    

    /**
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id)
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
     * @return Returns the historyName.
     */
    public String getHistoryName()
    {
        return historyName;
    }

    /**
     * @param historyName The historyName to set.
     */
    public void setHistoryName(String historyName)
    {
        this.historyName = historyName;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return Returns the sex.
     */
    public int getSex()
    {
        return sex;
    }

    /**
     * @param sex The sex to set.
     */
    public void setSex(int sex)
    {
        this.sex = sex;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * @return Returns the telephone.
     */
    public String getTelephone()
    {
        return telephone;
    }

    /**
     * @param telephone The telephone to set.
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email)
    {
        this.email = email;
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
     * @return Returns the organization.
     */
    public Set<Organization> getOrganization()
    {
        return organization;
    }

    /**
     * @param organization The organization to set.
     */
    public void setOrganization(Set<Organization> organization)
    {
        this.organization = organization;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the icon.
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * @param icon The icon to set.
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    /**
     * @return Returns the roles.
     */
    public Set<Role> getRoles()
    {
        return roles;
    }

    /**
     * @param roles The roles to set.
     */
    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
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
     * @return Returns the applys.
     */
    public Set<Apply> getApplys()
    {
        return applys;
    }

    /**
     * @param applys The applys to set.
     */
    public void setApplys(Set<Apply> applys)
    {
        this.applys = applys;
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

    /**
     * @return Returns the type.
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
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
