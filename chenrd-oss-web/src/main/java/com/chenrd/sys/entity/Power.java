/*
 * 文件名：Power.java
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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.example.Domain;

/**
 * 权限表
 * 
 * @author chenrd
 * @version 2015年5月12日
 * @see Power
 * @since
 */
@Table(name = "OSS_POWER")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_")
public class Power extends Domain {

	/**
	 * <br>
	 */
	private static final long serialVersionUID = 4817033998474822068L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 类型
	 */
	@QueryParams(defaultNotQuery = true, defaultNotQueryValue = 0)
	@Column(name = "TYPE_", length = 1, insertable = false, updatable = false)
	private int type;

	/**
	 * 名称
	 */
	@Column(name = "NAME_", length = 50)
	private String name;

	/**
	 * 001/002/003
	 */
	@QueryOrder(index = 0, value = "asc")
	@Column(name = "KEY_", length = 50, unique = true, nullable = false)
	private String key;

	/**
	 * 创建日期
	 */
	@Column(name = "CREATE_DATE")
	private Date createDate = new Date();

	/**
	 * 应用
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "APPLY_", nullable = false)
	private Apply apply;

	@Column(name = "APPLY_", insertable = false, updatable = false)
	private Long applyId;

	/**
	 * 菜单访问路径
	 */
	@Column(name = "URL_", length = 200)
	private String url;

	/**
	 * 
	 */
	@QueryParams
	@Column(name = "PARENT_KEY", length = 200)
	private String parentKey;

	/**
	 * 全称由：应用名称-父名称-本名称
	 */
	@Column(name = "FULL_NAME", length = 200)
	private String fullName;

	/**
	 * 状态
	 */
	@QueryParams(defaultNotQuery = true, nexus = Nexus.GTEQUAL)
	@Column(name = "STATUS")
	private int status;
	
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
		Power objKey = (Power) obj;
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
	 * @param id
	 *            The id to set.
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
	 * @param name
	 *            The name to set.
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
	 * @param key
	 *            The key to set.
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return Returns the serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the apply.
	 */
	public Apply getApply() {
		return apply;
	}

	/**
	 * @param apply
	 *            The apply to set.
	 */
	public void setApply(Apply apply) {
		this.apply = apply;
	}

	/**
	 * @return Returns the parentKey.
	 */
	public String getParentKey() {
		return parentKey;
	}

	/**
	 * @param parentKey
	 *            The parentKey to set.
	 */
	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	/**
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            The fullName to set.
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return Returns the status.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
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
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
