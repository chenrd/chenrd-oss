/*
 * 文件名：Attribute.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import com.chenrd.sys.service.PowerType;

/**
 * 属性权限 or 字段权限
 * @author chenrd
 * @version 2015年6月16日
 * @see Attribute
 * @since
 */
@Entity
@DiscriminatorValue("2")
public class Attribute extends Power
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 6135556372195673402L;
    
    /**
     * 
     */
    @Column(name = "VALUE_", length = 100)
    private String value;
    
    /**
     * 查询用的，属性权限目前只有三层机制，可以理解为
     * 1：class层
     * 2：属性层
     * 3：值
     */
    @Column(name = "ATTR_TYPE", length = 1)
    private int attrType;
    
    @Transient
    private String applyName;
    
    /**
     * 
     */
    public Attribute()
    {
        super();
        super.setType(PowerType.FIELD_POWER);
    }

    /**
     * @return Returns the applyName.
     */
    public String getApplyName()
    {
        return getApply().getName();
    }

    /**
     * @param applyName The applyName to set.
     */
    public void setApplyName(String applyName)
    {
        this.applyName = applyName;
    }

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the attrType.
     */
    public int getAttrType()
    {
        return attrType;
    }

    /**
     * @param attrType The attrType to set.
     */
    public void setAttrType(int attrType)
    {
        this.attrType = attrType;
    }
    

}
