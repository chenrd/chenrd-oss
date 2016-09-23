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

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chenrd.common.FreemakerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.sys.business.OrganizationManager;
import com.chenrd.sys.service.info.OrgInfo;

/**
 * 
 * @author chenrd
 * @version 2015年7月4日
 * @see OrganizationController
 * @since
 */
@Controller
@RequestMapping("org")
public class OrganizationController extends FreemakerController
{
    
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
    public String index()
    {
        return getViewName("view/org/index");
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "show/{id}", method = RequestMethod.GET)
    public String show(@PathVariable String id, ModelMap map)
    {
        map.put("id", id);
        return getViewName("view/org/rows");
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "find/{id}", method = RequestMethod.POST)
    public JQueryTableResult find(@PathVariable String id, Paging paging)
    {
        return new JQueryTableResult(orgManager.find(id, paging), paging);
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "{id}/find", method = RequestMethod.POST)
    public List<OrgInfo> find(@PathVariable String id)
    {
        return orgManager.find(id, null);
    }
}
