/*
 * 文件名：PowerCommonQueryInfo.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年9月30日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.info;

import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Example;
import com.chenrd.example.Status;

public class PowerCommonQueryInfo extends Example implements QueryInfo {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = 1254551773772652118L;

	private String parentKey;
	
	private int type;

	private int status = Status.OFF;

	/**
	 * @param status
	 */
	public PowerCommonQueryInfo(int status) {
		super();
		this.status = status;
	}

	/**
	 * @param parentKey
	 * @param status
	 */
	public PowerCommonQueryInfo(String parentKey, int status, int type) {
		super();
		this.parentKey = parentKey;
		this.status = status;
		this.type = type;
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
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}

}
