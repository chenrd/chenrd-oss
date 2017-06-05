/*
 * 文件名：MenuVO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.vo;

import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.example.VO;

/**
 * 
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuVO
 * @since
 */
public class MenuVO extends VO {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = 6921917533588824937L;

	/**
	 * 
	 */
	private Long id;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 这个KEY会根据页面需求替换成最后一部分 001/002/003
	 */
	private String key;

	/**
	 * 
	 */
	private String fullKey;

	/**
	 * 
	 */
	private String value;

	/**
	 * 创建日期
	 */
	private String createDate;

	/**
	 * 
	 */
	private Long applyId;

	/**
	 * 
	 */
	private String applyName;

	/**
	 * 菜单访问路径
	 */
	private String url;

	/**
	 * 样式
	 */
	private String icon;

	/**
	 * 
	 */
	private String parentKey;

	/**
	 * 
	 */
	private String parentName;

	/**
	 * 
	 */
	private String fullName;

	/**
	 * 
	 */
	private int status;

	private String[] funcNames;

	private String[] funcUrls;

	private Long[] funcIds;

	/**
	 * @param id
	 * @param name
	 * @param key
	 * @param parentKey
	 */
	@FindConstructor(name = "findSelect", value = "select new com.chenrd.sys.vo.FuncVO(po.id, po.name, po.key, po.parentKey, po.fullName) ")
	public MenuVO(Long id, String name, String key, String parentKey, String fullName) {
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
	 * @param applyName
	 */
	public MenuVO(Long id, String key, String name, String fullName) {
		super();
		this.id = id;
		this.key = key;
		this.name = name;
		this.fullName = fullName;
	}

	/**
	 * 
	 */
	public MenuVO() {
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
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            The type to set.
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return Returns the value.
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            The value to set.
	 */
	public void setValue(String value) {
		this.value = value;
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
	 * @return Returns the applyName.
	 */
	public String getApplyName() {
		return applyName;
	}

	/**
	 * @param applyName
	 *            The applyName to set.
	 */
	public void setApplyName(String applyName) {
		this.applyName = applyName;
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
	 * @return Returns the icon.
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon
	 *            The icon to set.
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}

	/**
	 * @return Returns the serialversionuid.
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return Returns the parentName.
	 */
	public String getParentName() {
		return parentName;
	}

	/**
	 * @param parentName
	 *            The parentName to set.
	 */
	public void setParentName(String parentName) {
		this.parentName = parentName;
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
	 * @return Returns the funcNames.
	 */
	public String[] getFuncNames() {
		return funcNames;
	}

	/**
	 * @param funcNames
	 *            The funcNames to set.
	 */
	public void setFuncNames(String[] funcNames) {
		this.funcNames = funcNames;
	}

	/**
	 * @return Returns the funcUrls.
	 */
	public String[] getFuncUrls() {
		return funcUrls;
	}

	/**
	 * @param funcUrls
	 *            The funcUrls to set.
	 */
	public void setFuncUrls(String[] funcUrls) {
		this.funcUrls = funcUrls;
	}

	/**
	 * @return Returns the funcIds.
	 */
	public Long[] getFuncIds() {
		return funcIds;
	}

	/**
	 * @param funcIds
	 *            The funcIds to set.
	 */
	public void setFuncIds(Long[] funcIds) {
		this.funcIds = funcIds;
	}

	/**
	 * @return Returns the fullKey.
	 */
	public String getFullKey() {
		return fullKey;
	}

	/**
	 * @param fullKey
	 *            The fullKey to set.
	 */
	public void setFullKey(String fullKey) {
		this.fullKey = fullKey;
	}

}
