/*
 * 文件名：ApplyVO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import java.util.Date;

import com.chenrd.common.DateUtil;
import com.chenrd.common.ocp.NoCopy;
import com.chenrd.common.ocp.NoCopy.NoCopyType;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.VO;

/**
 * 
 * 应用值类
 * @author chenrd
 * @version 2015年5月15日
 * @see ApplyVO
 * @since
 */
public class ApplyVO extends VO implements QueryInfo
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 947868468879873457L;
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 应用名称
     */
    private String name;
    
    /**
     * 唯一标识
     */
    private String key;
    
    /**
     * 应用地址
     */
    private String address;
    
    /**
     * 创建时间
     */
    private String createDate;
    
    /**
     * 创建者姓名
     */
    private String createName;
    
    private String createId;
    
    /**
     * 
     */
    @NoCopy(NoCopyType.Current)
    private int status;
    
    private String icon;
    
    
    /**
     * 
     */
    public ApplyVO()
    {
        super();
    }
    
    /**
     * @param status
     */
    public ApplyVO(int status)
    {
        super();
        this.status = status;
    }

    /**
     * 
     * @param id
     * @param name
     * @param key
     */
    @FindConstructor(name = "findSelect", value = "select new com.chenrd.sys.vo.ApplyVO(po.id, po.name, po.key) ")
    public ApplyVO(Long id, String name, String key)
    {
        super();
        this.id = id;
        this.name = name;
        this.key = key;
    }

    /**
     * @param id
     * @param name
     * @param key
     * @param address
     * @param createDate
     * @param createName
     * @param status
     */
    @FindConstructor(name = "find", value = "select new com.chenrd.sys.vo.ApplyVO(po.id, po.name, po.key, po.address, po.createDate, po.createName, po.status) ")
    public ApplyVO(Long id, String name, String key, String address, Date createDate,
                   String createName, int status)
    {
        super();
        this.id = id;
        this.name = name;
        this.key = key;
        this.address = address;
        this.createDate = DateUtil.formatDateTime(createDate);
        this.createName = createName;
        this.status = status;
    }

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
     * @return Returns the address.
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param address The address to set.
     */
    public void setAddress(String address)
    {
        this.address = address;
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
     * @return Returns the createName.
     */
    public String getCreateName()
    {
        return createName;
    }

    /**
     * @param createName The createName to set.
     */
    public void setCreateName(String createName)
    {
        this.createName = createName;
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
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
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
     * @return Returns the createId.
     */
    public String getCreateId()
    {
        return createId;
    }

    /**
     * @param createId The createId to set.
     */
    public void setCreateId(String createId)
    {
        this.createId = createId;
    }
    
}
