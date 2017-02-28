/*
 * 文件名：ApplyController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;

import java.util.ArrayList;
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
import com.chenrd.example.UserSessionParameter;
import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.business.ApplyManager;
import com.chenrd.sys.service.info.ApplyInfo;
import com.chenrd.sys.service.info.UserInfo;
import com.chenrd.sys.vo.ApplyVO;

/**
 * 应用管理的控制器
 * @author chenrd
 * @version 2015年5月18日
 * @see ApplyController
 * @since
 */
@Controller
@RequestMapping("apply")
public class ApplyController extends FreemarkerController
{
    
    /**
     * 
     */
    @Resource(name = "applyManager")
    private ApplyManager applyManager;
    
    /**
     * 
     */
    @Resource(name = "userEhcacheHandle")
    private UserEhcacheHandle userEhcacheHandle;
    
    /**
     * 
     * 返回显示应用列表页面
     * @return ""
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index()
    {
        return getViewName("view/apply/rows");
    }
    
    /**
     * 获取应用列表数据
     * @param name 名称
     * @param paging Paging
     * @return JQueryTableResult
     * @see
     */
    @RequestMapping(value = "/find", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult find(ApplyVO info, Paging paging) 
    {
        return new JQueryTableResult(applyManager.find("find", ApplyVO.class, info, paging), paging);
    }
    
    /**
     * 获取应用列表数据
     * @param name 名称
     * @param paging Paging
     * @return JQueryTableResult
     * @see
     */
    @RequestMapping(value = "/findSelect", method = RequestMethod.GET)
    @ResponseBody
    public List<ApplyVO> findSelect() 
    {
        return applyManager.find("findSelect", ApplyVO.class, new ApplyVO(Status.NO));
    }
    
    /**
     * 
     * 查询全部
     * @return List<?>
     * @see
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseBody
    public List<?> find(HttpServletRequest request)
    {
        if (!UserSessionParameter.OSS_DEFAULT_ADMIN.equals(request.getUserPrincipal().getName()))
        {
            List<ApplyInfo> list = new ArrayList<ApplyInfo>();
            UserInfo userInfo = userEhcacheHandle.get(request.getRequestedSessionId());
            list.addAll(userInfo.getApplys());
            return list;
        }
        else
        {
            return applyManager.find("findSelect", ApplyVO.class, new ApplyVO(Status.NO));
        }
    }
    
    /**
     * 
     * 保存或更新
     * @param apply Apply
     * @param request HttpServletRequest
     * @see
     */
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(ApplyVO apply, HttpServletRequest request)
    {
        if (apply.getId() != null)
        {
            apply.setCreateName((String) request.getSession().getAttribute(UserInfo.OSS_SESSION_USER_NAME));
            apply.setCreateId((String) request.getSession().getAttribute(UserInfo.OSS_SESSION_USER_ID));
        }
        applyManager.saveOrUpdate(apply);
    }
    
    /**
     * 
     * 返回编辑添加页面
     * @param id 应用ID 
     * @param map 
     * @return 页面
     * @see
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, ModelMap map) 
    {
        map.put("bean", applyManager.get(id, ApplyVO.class));
        return getViewName("view/apply/edit");
    }
    
    /**
     * 
     * 返回编辑添加页面
     * @param id 应用ID 
     * @param map 
     * @return 页面
     * @see
     */
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create() 
    {
        return getViewName("view/apply/edit");
    }
    
    /**
     * 删除
     * @param id id
     * @see
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id)
    {
        applyManager.delete(id);
    }
    
    /**
     * 
     * 发布
     * @param id 
     * @see
     */
    @RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void publish(@PathVariable Long id)
    {
        applyManager.publish(id);
    }

}
