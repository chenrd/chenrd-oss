/*
 * 文件名：ProcessDef.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.example.Domain;
import com.chenrd.example.Status;
import com.chenrd.oss.power.ann.DefClassPower;
import com.chenrd.oss.power.ann.DefFieldPower;

@DefClassPower({"流程", "100"})
@Entity
@Table(name = "workflow_process_def")
@SequenceGenerator(name = "sqe_workflow_process_def_id", sequenceName = "sqe_workflow_process_def_id", allocationSize = 1)
@NamedQueries({
	@NamedQuery(name = "queryInitoLoadDef", query = "from ProcessDef where status=1")
	})
public class ProcessDef extends Domain {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = -61143846498754412L;
	
	public static final String dcp_name = "流程", dcp_key = "100", fcp_name = "编号", fcp_key = "100";
	
	@Id @Column(name = "ID") @GeneratedValue(strategy = GenerationType.AUTO, generator = "sqe_workflow_process_def_id")
	private Long id;
	
	@DefFieldPower({"编号", "100"})
	@Column(name = "CODE", length = 32, nullable = false)
	private String code;
	
	@QueryParams
	@Column(name = "NAME", length = 64, nullable = false)
	private String name;
	
	@QueryOrder(index = 1, value = "desc")
	@Column(name = "STATUS", length = 1, nullable = false)
	private int status = Status.ON;
	
	@Column(name = "PATH", length = 128, nullable = false)
	private String path;
	
	@Column(name = "CREATOR", nullable = false)
	private String creator;
	
	@QueryOrder(index = 2, value = "desc")
	@Column(name = "CREATE_TIME", nullable = false) @Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
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
	 * @return Returns the createTime.
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime The createTime to set.
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
