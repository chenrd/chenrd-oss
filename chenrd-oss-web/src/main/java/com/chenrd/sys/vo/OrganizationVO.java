/*
 * 文件名：OrganizationVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import com.chenrd.common.ocp.NoCopy;
import com.chenrd.common.ocp.NoCopy.NoCopyType;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Status;
import com.chenrd.example.VO;

public class OrganizationVO extends VO implements QueryInfo {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = -2885488332234287795L;
	
	public static final OrganizationVO QUERY_YES_PUBLISH = new OrganizationVO(Status.ON); 
	
	private Long id;
    
    /**
     * 名称
     */
    private String name;
    
    private Long parentId;
    
    private String parentName;
    
    /**
     * 
     */
    private String key;
    
    /**
     * 全称
     */
    private String fullName;
    
    
    /**
     * 
     */
    private String leaderId;
    
    /**
     * 部门领导
     */
    private String leaderName;
    
    /**
     * 部门联系电话
     */
    private String contact;
    
    /**
     * 传真
     */
    private String fax;
    
    /**
     * 备注
     */
    private String remark;
    
    
    private String creator;
    
    @NoCopy(NoCopyType.Per)
    private int status = Status.OFF;


	/**
	 * @param id
	 * @param name
	 * @param key
	 * @param fullName
	 * @param leaderId
	 * @param leaderName
	 * @param contact
	 * @param fax
	 * @param order
	 * @param remark
	 */
    @FindConstructor(name = "find", value = "select new com.chenrd.sys.vo.OrganizationVO(po.id, po.name, po.key, po.fullName, po.leaderId, po.leaderName, po.contact, po.fax, po.remark, po.status) ")
	public OrganizationVO(Long id, String name, String key, String fullName, String leaderId, String leaderName,
			String contact, String fax, String remark, int status) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.fullName = fullName;
		this.leaderId = leaderId;
		this.leaderName = leaderName;
		this.contact = contact;
		this.fax = fax;
		this.remark = remark;
		this.status = status;
	}
    
	/**
	 * @param id
	 * @param name
	 * @param fullName
	 * @param leaderId
	 * @param leaderName
	 * @param parentId
	 * @param parentName
	 */
    @FindConstructor(name = "findSelectByService", value = "select new com.chenrd.sys.vo.OrganizationVO(po.id, po.key, po.name, po.fullName, po.leaderId, po.leaderName, po.parent.id, po.parent.name) ")
	public OrganizationVO(Long id, String key, String name, String fullName, String leaderId, String leaderName, Long parentId,
			String parentName) {
		super();
		this.id = id;
		this.key = key;
		this.name = name;
		this.fullName = fullName;
		this.leaderId = leaderId;
		this.leaderName = leaderName;
		this.parentId = parentId;
		this.parentName = parentName;
	}

	/**
	 * @param status
	 */
	public OrganizationVO(int status) {
		super();
		this.status = status;
	}

	/**
	 * 
	 */
	public OrganizationVO() {
		super();
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
	 * @return Returns the fullName.
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName The fullName to set.
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
	 * @param leaderId The leaderId to set.
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
	 * @param leaderName The leaderName to set.
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
	 * @param contact The contact to set.
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
	 * @param fax The fax to set.
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
	 * @param remark The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return Returns the creator.
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator The creator to set.
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	/**
	 * @return Returns the parentId.
	 */
	public Long getParentId() {
		return parentId;
	}
	
	/**
	 * @param parentId The parentId to set.
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return Returns the status.
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status The status to set.
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return Returns the parentName.
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName The parentName to set.
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
}
