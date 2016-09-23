/*
 * 文件名：ApplyInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import com.chenrd.example.VO;

/**
 * @author chenrd
 * @version 2015年7月10日
 * @see ApplyInfo
 * @since
 */
public class ApplyInfo extends VO
{
    
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -6504628555750370537L;

    private Long id;
    
    private String name;
    
    private String address;
    
    private String key;

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
    
}
