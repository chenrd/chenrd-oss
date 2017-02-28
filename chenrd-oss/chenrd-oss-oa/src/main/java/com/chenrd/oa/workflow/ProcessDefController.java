/*
 * 文件名：ProcessDefController.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.oa.workflow.business.ProcessDefManager;
import com.chenrd.oa.workflow.vo.ProcessDefVO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "def")
public class ProcessDefController extends FreemarkerController {
	
	@Resource(name = "processDefManager")
	private ProcessDefManager processDefManager;
	
	@RequestMapping(value = "")
	public String index() {
		return getViewName("view/def/rows");
	}
	
	@RequestMapping(value = "upload")
	public String upload() {
		return getViewName("view/def/upload");
	}
	
	@RequestMapping(value = "edit/{id}")
	public String edit(@PathVariable Long id, ModelMap map) {
		map.put("bean", processDefManager.get(id, ProcessDefVO.class));
		return getViewName("view/def/edit");
	}
	
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public JQueryTableResult find(ProcessDefVO info, Paging paging) {
		return new JQueryTableResult(processDefManager.find("find", ProcessDefVO.class, info, paging), paging);
	}
	
	@RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject saveOrUpdate(ProcessDefVO vo, MultipartFile file, HttpServletRequest request) {
		vo.setCreator(request.getUserPrincipal().getName());
		return processDefManager.saveOrUpdate(vo, file).toJSONObject();
	}
	
	@RequestMapping(value = "delete/{id}")
	@ResponseBody
	public void delete(@PathVariable Long id) {
		processDefManager.delete(id);
	}
	
}
