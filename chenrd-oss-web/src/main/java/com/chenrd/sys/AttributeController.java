/*
 * 文件名：AttributeController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月9日
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
import com.chenrd.example.Status;
import com.chenrd.sys.business.AttributeManager;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 
 * @author chenrd
 * @version 2015年7月9日
 * @see AttributeController
 * @since
 */
@Controller
@RequestMapping("attribute")
public class AttributeController extends FreemakerController
{
    
    /**
     * 
     */
    @Resource(name = "attributeManager")
    private AttributeManager attributeManager;
 
    /**
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index()
    {
        return getViewName("view/attribute/index");
    }
    
    /**
     * 
     * @param parentKey
     * @return 
     * @see
     */
    @RequestMapping(value = "show/{applyId}")
    public String show(@PathVariable Long applyId, String parentKey, ModelMap map)
    {
        map.put("applyId", applyId);
        map.put("parentKey", parentKey);
        return getViewName("view/attribute/rows");
    }
    
    /**
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Long applyId, String parentKey, ModelMap map)
    {
        map.put("applyId", applyId);
        map.put("parentKey", parentKey);
        return getViewName("view/attribute/edit");
    }
    
    /**
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id, Long applyId, String parentKey, ModelMap map)
    {
        map.put("applyId", applyId);
        map.put("parentKey", parentKey);
        map.put("bean", attributeManager.get(id, AttributeVO.class));
        return getViewName("view/attribute/edit");
    }
    
    /**
     * 
     *  
     * @see
     */
    @RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public void saveOrUpdate(AttributeVO vo)
    {
        attributeManager.saveOrUpdate(vo);
    }
    
    /**
     * 
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "find", method = RequestMethod.POST)
    @ResponseBody
    public JQueryTableResult find(AttributeVO info, Paging paging)
    {
        return new JQueryTableResult(attributeManager.find("find", AttributeVO.class, info, paging), paging);
    }
    
    /**
     * 
     * 
     * @return 
     * @see
     */
    @RequestMapping(value = "findChilds/{parentKey}", method = RequestMethod.GET)
    @ResponseBody
    public List<AttributeVO> findChilds(@PathVariable String parentKey)
    {
        return attributeManager.find("findChilds", AttributeVO.class, new AttributeVO(parentKey.replace("-", "/"), Status.OFF));
    }
    
    /**
     * 这个方法是用户字段分配的时候用的，状态必须为可用
     * 
     * @param parentKey
     * @return 
     * @see
     */
    @RequestMapping(value = "findSelect/{parentKey}")
    @ResponseBody
    public List<AttributeVO> findSelect(@PathVariable String parentKey)
    {
        return attributeManager.find("findChilds", AttributeVO.class, new AttributeVO(parentKey.replace("-", "/"), Status.ON));
    }
    
    /**
     * 
     * @param id
     * @return 
     * @see
     */
    @RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void publish(@PathVariable Long id)
    {
        attributeManager.publish(id);
    }
    
    
    /**
     * 删除
     * 
     * @param id 
     * @see
     */
    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void delete(@PathVariable Long id)
    {
        attributeManager.delete(id);
    }
}
