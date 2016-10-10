/*
 * 文件名：RoleVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年10月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import javax.persistence.Column;

import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.VO;

public class RoleVO extends VO implements QueryInfo
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -387574204665640124L;
    
    private Long id;
    
    /**
     * 
     */
    private String name;
    
    /**
     * 
     */
    private String key;
    
    /**
     * 
     */
    private String remark;
    
    /**
     * 
     */
    @Column(name = "STATUS")
    private int status;
    
    private String createUser;

    /**
     * 
     */
    public RoleVO()
    {
        super();
    }

    /**
     * @param id
     * @param name
     * @param key
     */
    @FindConstructor(name = "findSelect", value = "select new com.chenrd.sys.vo.RoleVO(po.id, po.name, po.key) ")
    public RoleVO(Long id, String name, String key)
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
     * @param remark
     * @param status
     */
    @FindConstructor(name = "find", value = "select new com.chenrd.sys.vo.RoleVO(po.id, po.name, po.key, po.remark, po.status) ")
    public RoleVO(Long id, String name, String key, String remark, int status)
    {
        super();
        this.id = id;
        this.name = name;
        this.key = key;
        this.remark = remark;
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

}
