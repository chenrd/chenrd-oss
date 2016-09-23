/*
 * 文件名：Organization.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.chenrd.example.Domain;


/**
 * 
 * 组织机构
 * @author xuwenqiang
 * @version 2015年5月12日
 * @see Organization
 * @since
 */
@Table(name = "OSS_ORGANIZATION")
@Entity
public class Organization extends Domain
{

    /**
     * <br>
     */
    private static final long serialVersionUID = -4439546572582914882L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "UUID", length = 32)
    private String id;
    
    /**
     * 名称
     */
    @Column(name = "NAME_", length = 50)
    private String name;
    
    /**
     * 
     */
    @Column(name = "KEY_")
    private String key;
    
    /**
     * 
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_")
    private Organization parent;
    
    /**
     * 控制power的权限
     */
    @Column(name = "POWER", length = 20)
    private String power;
    
    /**
     * 全称
     */
    @Column(name = "FULL_NAME", length = 100)
    private String fullName;
    
    
    /**
     * 
     */
    @Column(name = "LEADER_ID", length = 32)
    private String leaderId;
    
    /**
     * 部门领导
     */
    @Column(name = "LEADER_NAME", length = 50)
    private String leaderName;
    
    /**
     * 部门联系电话
     */
    @Column(name = "CONTACT_", length = 20)
    private String contact;
    
    /**
     * 传真
     */
    @Column(name = "FAX_", length = 20)
    private String fax;
    
    /**
     * 排序
     */
    @Column(name = "ORDER_", length = 5)
    private int order;
    
    /**
     * 备注
     */
    @Column(name = "REMARK", length = 200)
    private String remark;
    
    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate;
    
    /**
     * 删除时间
     */
    @Column(name = "DELETE_DATE")
    private Date deleteDate;
    
    /**
     * 删除人姓名
     */
    @Column(name = "DELETE_NAME", length = 50)
    private String deleteName;
    
    /**
     * 状态
     */
    @Column(name = "STATUS", length = 1)
    private int status;
    
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
     * @return Returns the fullName.
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * @param fullName The fullName to set.
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * @return Returns the leaderId.
     */
    public String getLeaderId()
    {
        return leaderId;
    }

    /**
     * @param leaderId The leaderId to set.
     */
    public void setLeaderId(String leaderId)
    {
        this.leaderId = leaderId;
    }

    /**
     * @return Returns the leaderName.
     */
    public String getLeaderName()
    {
        return leaderName;
    }

    /**
     * @param leaderName The leaderName to set.
     */
    public void setLeaderName(String leaderName)
    {
        this.leaderName = leaderName;
    }

    /**
     * @return Returns the contact.
     */
    public String getContact()
    {
        return contact;
    }

    /**
     * @param contact The contact to set.
     */
    public void setContact(String contact)
    {
        this.contact = contact;
    }

    /**
     * @return Returns the fax.
     */
    public String getFax()
    {
        return fax;
    }

    /**
     * @param fax The fax to set.
     */
    public void setFax(String fax)
    {
        this.fax = fax;
    }

    /**
     * @return Returns the order.
     */
    public int getOrder()
    {
        return order;
    }

    /**
     * @param order The order to set.
     */
    public void setOrder(int order)
    {
        this.order = order;
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
     * @return Returns the deleteDate.
     */
    public Date getDeleteDate()
    {
        return deleteDate;
    }

    /**
     * @param deleteDate The deleteDate to set.
     */
    public void setDeleteDate(Date deleteDate)
    {
        this.deleteDate = deleteDate;
    }

    /**
     * @return Returns the deleteName.
     */
    public String getDeleteName()
    {
        return deleteName;
    }

    /**
     * @param deleteName The deleteName to set.
     */
    public void setDeleteName(String deleteName)
    {
        this.deleteName = deleteName;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the parent.
     */
    public Organization getParent()
    {
        return parent;
    }

    /**
     * @param parent The parent to set.
     */
    public void setParent(Organization parent)
    {
        this.parent = parent;
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
     * @return Returns the power.
     */
    public String getPower()
    {
        return power;
    }

    /**
     * @param power The power to set.
     */
    public void setPower(String power)
    {
        this.power = power;
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
    
}
