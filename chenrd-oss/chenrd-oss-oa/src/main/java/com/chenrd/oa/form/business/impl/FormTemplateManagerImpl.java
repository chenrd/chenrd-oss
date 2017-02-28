/*
 * 文件名：FormTemplateManagerImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年1月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.form.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chenrd.dao.BaseDAO;
import com.chenrd.oa.form.business.FormTemplateManager;
import com.chenrd.oa.form.dao.FormTemplateDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;

@Service("formTemplateManager")
public class FormTemplateManagerImpl extends AbstractPowerBusiness implements FormTemplateManager {

	@Resource(name = "formTemplateDAO")
	private FormTemplateDAO formTemplateDAO;
	
	@Override
	public BaseDAO getDAO() {
		return formTemplateDAO;
	}

}
