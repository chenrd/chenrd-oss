/*
 * 文件名：UserVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import java.util.Date;

import com.chenrd.common.DateUtil;
import com.chenrd.common.ocp.DateFormat;
import com.chenrd.common.ocp.DateFormat.Format;
import com.chenrd.common.ocp.NoCopy;
import com.chenrd.common.ocp.NoCopy.NoCopyType;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Status;
import com.chenrd.example.VO;

/**
 * 
 * @author chenrd
 * @version 2016年7月6日
 * @see UserVO
 * @since
 */
public class UserVO extends VO implements QueryInfo
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 1579489472852362702L;
    
    @NoCopy(NoCopyType.Per)
    private String id;
    
    
    @NoCopy(NoCopyType.Current)
    private String username;
    
    @NoCopy(NoCopyType.Current)
    private String email;
    
    private String phone;
    
    private String historyName;
    
    private String name;
    
    private String telephone;
    
    private int sex;
    
    @NoCopy(NoCopyType.Per)
    private int status = Status.OFF;
    
    
    @NoCopy(NoCopyType.Per)
    @DateFormat(Format.yMdHms)
    private String createDate;
    
    private String createUser;
    
    private String updateUser;
    
    
    /**
     * 用于页面授权自动选中
     */
    @NoCopy(NoCopyType.Per)
    private String powerstrs;
    
    /**
     * 用于页面授权自动选中
     */
    @NoCopy(NoCopyType.Per)
    private String rolestrs;
    
    /**
     * 用于授权页面自动选中
     */
    @NoCopy(NoCopyType.Per)
    private String applystrs;
    
    

    /**
     * 
     */
    public UserVO()
    {
        super();
    }

    /**
     * @param id
     * @param username
     * @param phone
     * @param name
     * @param sex
     * @param status
     * @param createDate
     */
    @FindConstructor(name = "find", value = "select new com.chenrd.sys.vo.UserVO(po.id, po.username, po.phone, po.name, po.sex, po.status, po.createDate) ")
    public UserVO(String id, String username, String phone, String name, int sex, int status,
                  Date createDate)
    {
        super();
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.name = name;
        this.sex = sex;
        this.status = status;
        this.createDate = DateUtil.formatDateTime(createDate);
    }
    
    @FindConstructor(name = "findSelect", value = "select new com.chenrd.sys.vo.UserVO(po.id, po.username) ")
    public UserVO(String id, String username)
	{
    	super();
    	this.id = id;
    	this.username = username;
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
     * @return Returns the createDate.
     */
    public String getCreateDate()
    {
        return createDate;
    }

    /**
     * @param createDate The createDate to set.
     */
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    /**
     * @return Returns the powerstrs.
     */
    public String getPowerstrs()
    {
        return powerstrs;
    }

    /**
     * @param powerstrs The powerstrs to set.
     */
    public void setPowerstrs(String powerstrs)
    {
        this.powerstrs = powerstrs;
    }

    /**
     * @return Returns the rolestrs.
     */
    public String getRolestrs()
    {
        return rolestrs;
    }

    /**
     * @param rolestrs The rolestrs to set.
     */
    public void setRolestrs(String rolestrs)
    {
        this.rolestrs = rolestrs;
    }

    /**
     * @return Returns the applystrs.
     */
    public String getApplystrs()
    {
        return applystrs;
    }

    /**
     * @param applystrs The applystrs to set.
     */
    public void setApplystrs(String applystrs)
    {
        this.applystrs = applystrs;
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
}
