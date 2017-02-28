/*
 * 文件名：FullTextResultCode.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.common;

import net.sf.json.JSONObject;

/**
 * 全文返回错误代码集合
 * @author chenrd
 * @version 2017年2月10日
 * @see FullTextResultCode
 * @since
 */
public enum FullTextResultCode {
	
	success(1000, "success"),
	process_upload_error(4000, "上传流程文件失败");
	
	private int statusCode;
	
	private String message;
	
	/**
	 * @param code
	 * @param message
	 */
	private FullTextResultCode(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	public JSONObject toJSONObject() {
		JSONObject object = new JSONObject();
		object.put("statusCode", this.statusCode);
		object.put("message", this.message);
		return object;
	}
	
	/**
	 * @return Returns the code.
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param code The code to set.
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
}
