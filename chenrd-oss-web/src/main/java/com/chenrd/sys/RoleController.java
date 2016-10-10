/*
 * 文件名：RoleController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
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

import com.chenrd.common.FreemakerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.example.Status;
import com.chenrd.example.UserSessionParameter;
import com.chenrd.sys.business.FuncManager;
import com.chenrd.sys.business.MenuManager;
import com.chenrd.sys.business.RoleManager;
import com.chenrd.sys.entity.Func;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.info.PowerCommonQueryInfo;
import com.chenrd.sys.service.info.RoleInfo;
import com.chenrd.sys.vo.FuncVO;
import com.chenrd.sys.vo.MenuVO;
import com.chenrd.sys.vo.RoleVO;

/**
 * 
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleController
 * @since
 */
@Controller
@RequestMapping("role")
public class RoleController extends FreemakerController
{

    /**
     * 
     */
    @Resource(name = "roleManager")
    private RoleManager roleManager;
    
    /**
     * 
     */
    @Resource(name = "menuManager")
    private MenuManager menuManager;
    
    /**
     * 
     */
    @Resource(name = "funcManager")
    private FuncManager funcManager;
    
    /**
     * 
     * 
     * @return ""
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index()
    {
        return getViewName("view/role/rows");
    }
    
    /**
     * 
     * 查询列表数据
     * @param name 名称
     * @param paging 分页条件
     * @return JQueryTableResult
     * @see
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult findPaging(RoleVO info, Paging paging)
    {
        return new JQueryTableResult(roleManager.find("find", RoleVO.class, info, paging), paging);
    }
    
    
    /**
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "findSelect", method = RequestMethod.GET)
    @ResponseBody
    public List<RoleVO> findSelect()
    {
        return roleManager.find("findSelect", RoleVO.class, new RoleVO());
    }
    
    /**
     * 
     * 返回编辑页面
     * @param id 
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap map, HttpServletRequest request)
    {
        if (UserSessionParameter.OSS_DEFAULT_ADMIN.equals(request.getUserPrincipal().getName())) {
            map.put("funcs", funcManager.find("findSelect", Func.class, FuncVO.class, new PowerCommonQueryInfo(Status.ON)));
            map.put("menus", menuManager.find("findSelect", Menu.class, MenuVO.class, new PowerCommonQueryInfo(Status.ON)));
        } else {
            map.put("menus", menuManager.findByUsername(request.getUserPrincipal().getName()));
            map.put("funcs", funcManager.findByUsername(request.getUserPrincipal().getName()));
        }
        map.put("bean", roleManager.get(id));
        return getViewName("view/role/edit");
    }
    
    /**
     * 
     * 返回编辑页面
     * @param id 
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Long id, ModelMap map, HttpServletRequest request)
    {
        if (UserSessionParameter.OSS_DEFAULT_ADMIN.equals(request.getUserPrincipal().getName())) {
            map.put("funcs", funcManager.find("findSelect", Func.class, FuncVO.class, new PowerCommonQueryInfo(Status.ON)));
            map.put("menus", menuManager.find("findSelect", Menu.class, MenuVO.class, new PowerCommonQueryInfo(Status.ON)));
        } else {
            map.put("menus", menuManager.findByUsername(request.getUserPrincipal().getName()));
            map.put("funcs", funcManager.findByUsername(request.getUserPrincipal().getName()));
        }
        return getViewName("view/role/edit");
    }
    
    /**
     * 
     * @param info 
     * @param powers 
     * @return 
     * @see
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(RoleInfo info, Long[] powers, HttpServletRequest request)
    {
        if (info.getId() == null)
        {
            info.setCreateUser(request.getUserPrincipal().getName());
        }
        info.setUpdateUser(request.getUserPrincipal().getName());
        roleManager.saveOrUpdate(info, powers);
    }
    
    /**
     * 
     * @param id the id
     * @see
     */
    @RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void publish(@PathVariable Long id)
    {
        roleManager.publish(id);
    }
    
    /**
     * 
     * @param id the id
     * @see
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id)
    {
        roleManager.delete(id);
    }
    

    /**
     * @param roleManager The roleManager to set.
     */
    public void setRoleManager(RoleManager roleManager)
    {
        this.roleManager = roleManager;
    }
    
    
    
}
