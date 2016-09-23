/*
 * 文件名：PowerInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import com.chenrd.example.VO;

/**
 * 
 * 权限信息表
 * @author chenrd
 * @version 2015年5月14日
 * @see PowerInfo
 * @since
 */
public class PowerInfo extends VO
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -3313795828796110369L;
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 类型
     */
    private int type;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 001/002/003
     */
    private String key;
    
    /**
     * 
     */
    private String value;
    
    /**
     * 创建日期
     */
    private String createDate;
    
    /**
     * 菜单访问路径
     */
    private String url;
    
    /**
     * 
     */
    private int status;
    
    /**
     * 
     */
    private String parentKey;
    
    /**
     * 
     */
    private Long applyId;
    
    /**
     * 
     */
    private String applyKey;
    
    /**
     * 
     */
    private String fullName;
    
    
    

    /**
     * @param name
     * @param key
     * @param url
     * @param parentKey
     * @param applyId
     * @param applyKey
     */
    public PowerInfo(Long id, String name, String key, String url, String parentKey, Long applyId,
                     String applyKey)
    {
        super();
        this.id = id;
        this.name = name;
        this.key = key;
        this.url = url;
        this.parentKey = parentKey;
        this.applyId = applyId;
        this.applyKey = applyKey;
    }

    /**
     * 
     */
    public PowerInfo()
    {
        super();
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
     * 
     * @return 
     * @see
     */
    public String getLastKey()
    {
        return key.substring(key.lastIndexOf("/") + 1);
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
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url)
    {
        this.url = url;
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
     * @return Returns the parentKey.
     */
    public String getParentKey()
    {
        return parentKey;
    }

    /**
     * @param parentKey The parentKey to set.
     */
    public void setParentKey(String parentKey)
    {
        this.parentKey = parentKey;
    }

    /**
     * @return Returns the applyId.
     */
    public Long getApplyId()
    {
        return applyId;
    }

    /**
     * @param applyId The applyId to set.
     */
    public void setApplyId(Long applyId)
    {
        this.applyId = applyId;
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

}
