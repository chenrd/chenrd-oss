/*
 * 文件名：FormController.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年1月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.form;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.oa.form.business.FormTemplateManager;
import com.chenrd.oa.form.vo.FormTemplateVO;

@Controller
@RequestMapping("form")
public class FormController extends FreemarkerController {

	@Resource(name = "formTemplateManager")
	private FormTemplateManager formTemplateManager;
	
	@RequestMapping("template")
	public String index() {
		return getViewName("view/template-rows");
	}
	
	@RequestMapping(value = "findTemplate", method = RequestMethod.POST)
	@ResponseBody
	public JQueryTableResult find(FormTemplateVO info, Paging paging) {
		return new JQueryTableResult(formTemplateManager.find("find", FormTemplateVO.class, info, paging), paging);
	}
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return getViewName("view/template-editor");
	}
	
	@RequestMapping(value = "input/{type}", method = RequestMethod.GET)
	public String input(@PathVariable String type) {
		return getViewName("view/form/input-" + type);
	}
}
