/*
 * 文件名：RoleInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import com.chenrd.example.VO;

/**
 * 
 * @author chenrd
 * @version 2015年6月15日
 * @see RoleInfo
 * @since
 */
public class RoleInfo extends VO {
	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = 7861344721488766993L;

	/**
	 * 
	 */
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
	private String createDate;

	/**
	 * 
	 */
	private int status;

	/**
	 * 
	 */
	private String powers;

	private String createUser;

	private String updateDate;

	private String updateUser;
	
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
		if (!(obj instanceof RoleInfo)) {
			return false;
		}
		RoleInfo objKey = (RoleInfo) obj;
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
	 * @return Returns the createDate.
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            The createDate to set.
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return Returns the powers.
	 */
	public String getPowers() {
		return powers;
	}

	/**
	 * @param powers
	 *            The powers to set.
	 */
	public void setPowers(String powers) {
		this.powers = powers;
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
	 * @return Returns the updateDate.
	 */
	public String getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate
	 *            The updateDate to set.
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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

}
