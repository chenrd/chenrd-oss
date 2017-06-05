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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.example.Domain;
import com.chenrd.example.Status;

/**
 * 
 * 组织机构
 * 
 * @author xuwenqiang
 * @version 2015年5月12日
 * @see Organization
 * @since
 */
@Table(name = "OSS_ORGANIZATION")
@Entity
@SequenceGenerator(name = "seq_org_id", sequenceName = "seq_org_id", allocationSize = 1)
public class Organization extends Domain {

	/**
	 * <br>
	 */
	private static final long serialVersionUID = -4439546572582914882L;

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator = "seq_org_id", strategy = GenerationType.AUTO)
	@Column(name = "ID", length = 32)
	private Long id;

	/**
	 * 名称
	 */
	@QueryParams
	@Column(name = "NAME_", length = 50)
	private String name;

	/**
	 * 
	 */
	@QueryOrder(index = 1)
	@Column(name = "KEY_", unique = true)
	private String key;

	/**
	 * 
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_", insertable = false, updatable = false)
	private Organization parent;

	@QueryParams
	@Column(name = "PARENT_")
	private Long parentId;

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
	 * 备注
	 */
	@Column(name = "REMARK", length = 200)
	private String remark;

	/**
	 * 创建时间
	 */
	@Column(name = "CREATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;

	@Column(name = "CREATE_USER", length = 64)
	private String createUser;

	@Column(name = "UPDATE_TIME", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Column(name = "UPDATE_USER", length = 64)
	private String updateUser;

	/**
	 * 状态
	 */
	@QueryParams(nexus = Nexus.GTEQUAL)
	@Column(name = "STATUS", length = 1)
	private Integer status = Status.ON;

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
	 * @return Returns the leaderId.
	 */
	public String getLeaderId() {
		return leaderId;
	}

	/**
	 * @param leaderId
	 *            The leaderId to set.
	 */
	public void setLeaderId(String leaderId) {
		this.leaderId = leaderId;
	}

	/**
	 * @return Returns the leaderName.
	 */
	public String getLeaderName() {
		return leaderName;
	}

	/**
	 * @param leaderName
	 *            The leaderName to set.
	 */
	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	/**
	 * @return Returns the contact.
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            The contact to set.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return Returns the fax.
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            The fax to set.
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return Returns the serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return Returns the parent.
	 */
	public Organization getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            The parent to set.
	 */
	public void setParent(Organization parent) {
		this.parent = parent;
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
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime
	 *            The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return Returns the createUser.
	 */
	public String getCreateUser() {
		return createUser;
	}

	/**
	 * @param createUser
	 *            The createUser to set.
	 */
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	/**
	 * @return Returns the updateTime.
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime
	 *            The updateTime to set.
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	/**
	 * @return Returns the updateUser.
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser
	 *            The updateUser to set.
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return Returns the parentId.
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            The parentId to set.
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
