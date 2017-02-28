/*
 * 文件名：FormTemplate.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年1月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.form.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.chenrd.example.Domain;

@Entity
@Table(name = "form_template")
@SequenceGenerator(name = "seq_form_template_id", sequenceName = "seq_form_template_id", allocationSize = 1)
public class FormTemplate extends Domain {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = -188193183025010652L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "seq_form_template_id", strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", length = 64, nullable = false)
	private String name;
	
	@Lob
	@Column(name = "HTML")
	private String html;
	
	@Column(name = "CREATOR", length = 32, nullable = false)
	private String creator;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_TIME", nullable = false)
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
	 * @return Returns the html.
	 */
	public String getHtml() {
		return html;
	}

	/**
	 * @param html The html to set.
	 */
	public void setHtml(String html) {
		this.html = html;
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
