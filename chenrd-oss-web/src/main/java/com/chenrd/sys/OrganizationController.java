/*
 * 文件名：OrganizationController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.example.Status;
import com.chenrd.sys.business.OrganizationManager;
import com.chenrd.sys.vo.OrganizationVO;

/**
 * 
 * @author chenrd
 * @version 2015年7月4日
 * @see OrganizationController
 * @since
 */
@Controller
@RequestMapping("org")
public class OrganizationController extends FreemarkerController {
    
    /**
     * 
     */
    @Resource(name = "orgManager")
    private OrganizationManager orgManager;
    
    /**
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return getViewName("view/org/index");
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create() {
        return getViewName("view/org/edit");
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap map) {
    	map.put("bean", orgManager.get(id, OrganizationVO.class));
        return getViewName("view/org/edit");
    }
    
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(OrganizationVO vo, HttpServletRequest request) {
    	vo.setCreator(request.getUserPrincipal().getName());
    	orgManager.saveOrUpdate(vo);
    }
    
    @RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void publish(@PathVariable Long id) {
    	orgManager.publish(id);
    }
    
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id) {
    	orgManager.delete(id);
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult find(OrganizationVO info, Paging paging) {
        return new JQueryTableResult(orgManager.find("find", OrganizationVO.class, info, paging), paging);
    }
    
    @RequestMapping(value = "findSelect")
    @ResponseBody
    public List<OrganizationVO> findSelect(OrganizationVO info) {
    	if (info.getParentId() == null) {
    		info.setName(OrganizationManager.DEFUALT_ORG_NAME);
    	}
    	info.setStatus(Status.ON);
    	return orgManager.find("find", OrganizationVO.class, info);
    }
    
    @RequestMapping(value = "findSelectAll")
    @ResponseBody
    public List<OrganizationVO> findSelectAll() {
    	return orgManager.find("find", OrganizationVO.class, OrganizationVO.QUERY_YES_PUBLISH);
    }
}
