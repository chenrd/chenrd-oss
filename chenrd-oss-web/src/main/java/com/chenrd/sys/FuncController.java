/*
 * 文件名：FuncController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;

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
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.ApplyVO;

/**
 * 功能权限控制器
 * @author chenrd
 * @version 2015年6月16日
 * @see FuncController
 * @since
 */
@RequestMapping("func")
@Controller
public class FuncController extends FreemakerController
{
    
    /**
     * 
     */
    @Resource(name = "funcManager")
    private FuncManager funcManager;
    
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
     * @return 返回首页
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index()
    {
        return getViewName("view/func/index");
    }
    
    /**
     * 
     * 显示列表页面
     * @param applyId 应用ID
     * @param parentKey 父节点key
     * @param map ModelMap
     * @return ""
     * @see
     */
    @RequestMapping(value = "show/{applyId}", method = RequestMethod.GET)
    public String show(@PathVariable Long applyId, String parentKey, ModelMap map)
    {
        map.put("applyId", applyId);
        map.put("parentKey", parentKey);
        return getViewName("view/func/rows");
    }
    
    /**
     * 
     * 编辑
     * @param id 
     * @param applyId 
     * @param parentKey 
     * @param map 
     * @return ""
     * @see
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Long applyId, String parentKey, ModelMap map)
    {
        if (id != null)
        {
            map.put("bean", funcManager.get(id));
        }
        map.put("apply", applyManager.get(applyId, ApplyVO.class));
        map.put("menu", menuManager.getByKey(parentKey));
        map.put("parentKey", parentKey);
        return getViewName("view/func/edit");
    }
    
    /**
     * 
     * 
     * @param id 
     * @param applyId 
     * @param parentKey 
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
        return getViewName("view/func/edit");
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
        return new JQueryTableResult(funcManager.findChilds(applyId, parentKey, paging), paging);
    }
    
    /**
     * 
     * @param info 权限
     * @see
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(PowerInfo info)
    {
        funcManager.saveOrUpdate(info);
    }
    
    /**
     * 
     * 发布
     * @param id ""
     * @see
     */
    @RequestMapping(value = "publish", method = RequestMethod.GET)
    @ResponseBody
    public void publish(Long id)
    {
        funcManager.publish(id);
    }
    
    /**
     * 
     * 删除
     * @param id ""
     * @see
     */
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    @ResponseBody
    public void delete(Long id)
    {
        funcManager.deleted(id);
    }
    
}
