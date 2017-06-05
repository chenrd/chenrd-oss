/*
 * 文件名：Apply.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2015年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.example.Domain;
import com.chenrd.sys.service.Status;

/**
 * 
 * 应用
 * @author chenrd
 * @version 2015年5月12日
 * @see Apply
 * @since
 */
@Table(name = "OSS_APPLY")
@Entity
public class Apply extends Domain {

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 8418676473846479762L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    
    /**
     * 应用名称
     */
    @QueryParams
    @QueryOrder(value = "asc", index = 2)
    @Column(name = "NAME_", length = 50)
    private String name;
    
    /**
     * KEY
     */
    @QueryParams
    @Column(name = "KEY_", length = 50, nullable = false, unique = true)
    private String key;
    
    /**
     * 应用地址
     */
    @Column(name = "ADDRESS_", length = 200)
    private String address;
    
    /**
     * 
     */
    @Column(name = "ICON_", length = 50)
    private String icon;
    
    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate = new Date();
    
    /**
     * 创建者ID
     */
    @Column(name = "CREATE_ID", length = 32)
    private String createId;
    
    /**
     * 创建者姓名
     */
    @Column(name = "CREATE_NAME", length = 50)
    private String createName;
    
    /**
     * 状态
     */
    @QueryOrder(value = "desc", index = 1)
    @QueryParams(nexus = Nexus.GTEQUAL)
    @Column(name = "STATUS")
    private Integer status = Status.OFF;
    
    /**
     * 访问路径
     */
    @Column(name = "URL")
    private String url;
    
	@Override
	public int hashCode() {
		int h = id.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Role)) {
			return false;
		}
		Apply objKey = (Apply) obj;
		if (objKey.id == this.id) {
			return true;
		}
		return false;
	}

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the key.
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return Returns the address.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address The address to set.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return Returns the icon.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon The icon to set.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the createId.
	 */
	public String getCreateId() {
		return createId;
	}

	/**
	 * @param createId The createId to set.
	 */
	public void setCreateId(String createId) {
		this.createId = createId;
	}

	/**
	 * @return Returns the createName.
	 */
	public String getCreateName() {
		return createName;
	}

	/**
	 * @param createName The createName to set.
	 */
	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
