/*
 * 文件名：FormTemplateVO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年1月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.form.vo;

import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.VO;

public class FormTemplateVO extends VO implements QueryInfo {

	/**
	 * 意义，目的和功能，以及被用到的地方<br>
	 */
	private static final long serialVersionUID = 8406515503752607355L;
	
	private Long id;
	
	private String name;
	
	private String html;

	/**
	 * 
	 */
	public FormTemplateVO() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param html
	 */
	@FindConstructor(name = "find", value = "select new com.chenrd.oa.form.vo.FormTemplateVO(po.id, po.name, po.html) ")
	public FormTemplateVO(Long id, String name, String html) {
		super();
		this.id = id;
		this.name = name;
		this.html = html;
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
	
}
