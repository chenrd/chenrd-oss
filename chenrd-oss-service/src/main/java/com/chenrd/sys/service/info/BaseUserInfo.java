/*
 * 文件名：BaseUserInfo.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2015年11月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import java.io.Serializable;

/**
 * 
 * 
 * @author chenrd
 * @version 2015年11月9日
 * @see BaseUserInfo
 * @since
 */
public class BaseUserInfo implements Serializable
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -5671074735016105234L;
    
    private String id;
    
    private String username;
    
    private String name;
    
    private int sex;
    
    private String phone;
    
    /**
     * 电话
     */
    private String telephone;
    
    /**
     * 邮箱
     */
    private String email;
    
    private String createUser;
    
    private String updateUser;
    
    private String defaultRole;

    public BaseUserInfo(String username, String name, String createUser, String updateUser)
    {
        super();
        this.username = username;
        this.name = name;
        this.createUser = createUser;
        this.updateUser = updateUser;
    }

    public BaseUserInfo()
    {
        super();
    }

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
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
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
     * @return Returns the defaultRole.
     */
    public String getDefaultRole()
    {
        return defaultRole;
    }

    /**
     * @param defaultRole The defaultRole to set.
     */
    public void setDefaultRole(String defaultRole)
    {
        this.defaultRole = defaultRole;
    }
    
}
