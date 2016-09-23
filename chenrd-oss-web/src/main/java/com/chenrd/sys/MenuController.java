/*
 * 文件名：MenuController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenrd.common.FreemakerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.sys.business.ApplyManager;
import com.chenrd.sys.business.FuncManager;
import com.chenrd.sys.business.MenuManager;
import com.chenrd.sys.vo.ApplyVO;
import com.chenrd.sys.vo.MenuVO;

/**
 * 菜单权限控制器
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuController
 * @since
 */
@RequestMapping("menu")
@Controller
public class MenuController extends FreemakerController
{
    
    /**
     * 
     */
    @Resource(name = "applyManager")
    private ApplyManager applyManager;
    
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
     * 返回菜单权限首页
     * @return ""
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() 
    {
        return getViewName("view/menu/index");
    }
    
    /**
     * 
     * 编辑
     * @param id ""
     * @param applyId 应用ID
     * @param parentKey 上级KEY
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Long applyId, String parentKey, ModelMap map) 
    {
        if (id != null)
        {
            MenuVO bean = menuManager.get(id);
            map.put("bean", bean);
            map.put("childs", funcManager.findByParentKey(bean.getFullKey()));
        }
        map.put("apply", applyManager.get(applyId, ApplyVO.class));
        map.put("menu", menuManager.getByKey(parentKey));
        map.put("parentKey", parentKey);
        map.put("menus", menuManager.findAll());
        return getViewName("view/menu/edit");
    }
    
    /**
     * 
     * 添加
     * @param id ""
     * @param applyId 应用ID
     * @param parentKey 上级KEY
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Long id, Long applyId, String parentKey, ModelMap map) 
    {
        map.put("apply", applyManager.get(applyId, ApplyVO.class));
        map.put("menu", menuManager.getByKey(parentKey));
        map.put("parentKey", parentKey);
        return getViewName("view/menu/edit");
    }
    
    /**
     * 
     * 返回列表页面
     * @param applyId 应用ID
     * @param parentKey 
     * @param map ModelMap
     * @return ""
     * @see
     */
    @RequestMapping(value = "show/{applyId}", method = RequestMethod.GET)
    public String show(@PathVariable String applyId, String parentKey, ModelMap map)
    {
        map.put("applyId", applyId);
        map.put("parentKey", parentKey);
        return getViewName("view/menu/rows");
    }
    
    /**
     * 
     * 
     * @param vo ApplyVO
     * @see
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(MenuVO vo) 
    {
        menuManager.saveOrUpdate(vo);
    }
    
    /**
     * 
     * 查询应用下面的一级目录
     * @param applyId 应用ID
     * @param parentKey 菜单KEY
     * @param paging 分页条件
     * @return List<MenuVO>
     * @see
     */
    @RequestMapping(value = "/findChilds/{applyId}", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuVO> findChilds(@PathVariable Long applyId, String parentKey, Paging paging) 
    {
        return menuManager.findChilds(applyId, parentKey, null);
    }
    
    /**
     * 
     * 查询获取菜单列表数据
     * @param applyId 应用ID
     * @param parentKey 菜单KEY
     * @param paging 分页条件
     * @return JQueryTableResult
     * @see
     */
    @RequestMapping(value = "/find/{applyId}", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult findPaging(@PathVariable Long applyId, String parentKey, Paging paging)
    {
        return new JQueryTableResult(menuManager.findChilds(applyId, parentKey, paging), paging);
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
        menuManager.publish(id);
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
        menuManager.deleted(id);
    }
    

    /**
     * @param menuManager The menuManager to set.
     */
    public void setMenuManager(MenuManager menuManager)
    {
        this.menuManager = menuManager;
    }
    
}
