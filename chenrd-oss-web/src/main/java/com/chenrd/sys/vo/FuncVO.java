/*
 * 文件名：FuncVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年9月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.example.VO;

public class FuncVO extends VO {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = -4443639835439573628L;

	private Long id;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 001/002/003
	 */
	private String key;

	/**
	 * 应用
	 */
	private Long applyId;

	/**
	 * 菜单访问路径
	 */
	private String url;

	/**
	 * 
	 */
	private String parentKey;

	/**
	 * 全称由：应用名称-父名称-本名称
	 */
	private String fullName;

	/**
	 * 状态
	 */
	private int status;

	/**
	 * @param id
	 * @param name
	 * @param key
	 * @param parentKey
	 */
	@FindConstructor(name = "findSelect", value = "select new com.chenrd.sys.vo.FuncVO(po.id, po.name, po.key, po.parentKey, po.fullName) ")
	public FuncVO(Long id, String name, String key, String parentKey, String fullName) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.parentKey = parentKey;
		this.fullName = fullName;
	}
	
	

	/**
	 * @param id
	 * @param name
	 * @param key
	 * @param applyId
	 * @param url
	 * @param parentKey
	 * @param fullName
	 * @param status
	 */
	@FindConstructor(name = "find", value = "select new com.chenrd.sys.vo.FuncVO(po.id, po.name, po.key, po.applyId, po.url, po.parentKey, po.fullName, po.status) ")
	public FuncVO(Long id, String name, String key, Long applyId, String url, String parentKey, String fullName, int status) {
		super();
		this.id = id;
		this.name = name;
		this.key = key;
		this.applyId = applyId;
		this.url = url;
		this.parentKey = parentKey;
		this.fullName = fullName;
		this.status = status;
	}



	/**
	 * 
	 */
	public FuncVO() {
		super();
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
	 * @return Returns the applyId.
	 */
	public Long getApplyId() {
		return applyId;
	}

	/**
	 * @param applyId
	 *            The applyId to set.
	 */
	public void setApplyId(Long applyId) {
		this.applyId = applyId;
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

}
