/*
 * 文件名：ProcessDefVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.vo;

import java.util.Date;

import com.chenrd.common.DateUtil;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Status;
import com.chenrd.example.VO;

public class ProcessDefVO extends VO implements QueryInfo {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = 5909334845559653771L;
	
	private Long id;
	
	private String code;
	
	private String name;
	
	private int status = Status.ON;
	
	private String path;
	
	private String creator;
	
	private String createTime;

	/**
	 * @param id
	 * @param code
	 * @param name
	 * @param status
	 * @param path
	 * @param creator
	 * @param createTime
	 */
	@FindConstructor(name = "find", value = "select new com.chenrd.oa.workflow.vo.ProcessDefVO(po.id, po.code, po.name, po.status, po.path, po.creator, po.createTime) ")
	public ProcessDefVO(Long id, String code, String name, int status, String path, String creator, Date createTime) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.status = status;
		this.path = path;
		this.creator = creator;
		this.createTime = DateUtil.formatDateTime(createTime);
	}

	/**
	 * 
	 */
	public ProcessDefVO() {
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
	 * @return Returns the code.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code The code to set.
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * @return Returns the path.
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path The path to set.
	 */
	public void setPath(String path) {
		this.path = path;
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
	 * @return Returns the createTime.
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime The createTime to set.
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
