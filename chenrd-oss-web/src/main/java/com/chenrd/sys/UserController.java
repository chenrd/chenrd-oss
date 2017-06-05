/*
 * 文件名：UserController.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.chenrd.common.FreemarkerController;
import com.chenrd.common.JQueryTableResult;
import com.chenrd.common.Paging;
import com.chenrd.example.Status;
import com.chenrd.example.UserSessionParameter;
import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.business.ApplyManager;
import com.chenrd.sys.business.FuncManager;
import com.chenrd.sys.business.MenuManager;
import com.chenrd.sys.business.RoleManager;
import com.chenrd.sys.business.UserManager;
import com.chenrd.sys.entity.Func;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.info.PowerCommonQueryInfo;
import com.chenrd.sys.vo.ApplyVO;
import com.chenrd.sys.vo.FuncVO;
import com.chenrd.sys.vo.MenuVO;
import com.chenrd.sys.vo.RoleVO;
import com.chenrd.sys.vo.UserVO;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserController
 * @since
 */
@RequestMapping("user")
@Controller
public class UserController extends FreemarkerController {

	@Resource(name = "userManager")
	private UserManager userManager;

	@Resource(name = "menuManager")
	private MenuManager menuManager;

	/**
	 * 
	 */
	@Resource(name = "funcManager")
	private FuncManager funcManager;

	/**
	 * 
	 */
	@Resource(name = "roleManager")
	private RoleManager roleManager;

	/**
	 * 
	 */
	@Resource(name = "applyManager")
	private ApplyManager applyManager;

	@Resource(name = "userEhcacheHandle")
	private UserEhcacheHandle userEhcacheHandle;

	/**
	 * 
	 * 
	 * @return ""
	 * @see
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index() {
		return getViewName("view/user/rows");
	}

	/**
	 * 分页查询
	 * 
	 * @param name
	 * @param phone
	 * @param paging
	 * @return {@link JQueryTableResult}
	 * @see
	 */
	@RequestMapping(value = "find", method = RequestMethod.POST)
	@ResponseBody
	public JQueryTableResult findPaging(UserVO info, Paging paging) {
		return new JQueryTableResult(userManager.find("find", UserVO.class, info, paging), paging);
	}

	@RequestMapping(value = "findSelect")
	@ResponseBody
	public List<UserVO> findSelect() {
		return userManager.find("findSelect", UserVO.class, UserVO.USABLE_USER);
	}

	@RequestMapping(value = "findJsonp", method = RequestMethod.GET)
	@ResponseBody
	public JSONPObject findJsonp(String callback, HttpServletRequest request) {
		List<UserVO> list = userManager.find("findSelect", UserVO.class, UserVO.USABLE_USER);
		return new JSONPObject(callback, list);
	}

	/**
	 * 添加
	 * @param id 用户ID
	 * @param map
	 * @return ""
	 * @see
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String create() {
		return getViewName("view/user/edit");
	}

	/**
	 * 编辑
	 * @param id 用户ID
	 * @param map
	 * @return ""
	 * @see
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable String id, ModelMap map) {
		map.put("bean", userManager.get(id, UserVO.class));
		return getViewName("view/user/edit");
	}

	/**
	 * 保存并更新
	 * @param info UserInfo
	 * @see
	 */
	@RequestMapping(value = "saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public void saveOrUpdate(UserVO vo, HttpServletRequest request) {
		if (StringUtils.isBlank(vo.getId())) {
			vo.setCreateUser(request.getUserPrincipal().getName());
		}
		vo.setUpdateUser(request.getUserPrincipal().getName());
		userManager.saveOrUpdate(vo);
	}

	/**
	 * 修改密码
	 * @see
	 */
	@RequestMapping(value = "updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public void updatePassword(HttpServletRequest request, String oldPassword, String newPassword) {
		String username = request.getUserPrincipal().getName();
		userManager.updatePassword(username, oldPassword, newPassword);
	}

	/**
	 * 发布或者取消发布
	 * @param id 用户ID
	 * @param publish 是否发布
	 * @see
	 */
	@RequestMapping(value = "publish/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void publish(@PathVariable String id, Boolean publish) {
		userManager.publish(id);
	}

	/**
	 * 删除用户
	 * @param id ID
	 * @see
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void delete(@PathVariable String id) {
		userManager.delete(id);
	}

	/**
	 * 授权 返回授权页面
	 * @param id ID
	 * @param map ModelMap
	 * @return ""
	 * @see
	 */
	@RequestMapping(value = "allot/{id}", method = RequestMethod.GET)
	public String allot(@PathVariable String id, ModelMap map, HttpServletRequest request) {
		// 系统默认管理员ROOT可以查询所有的权限
		if (UserSessionParameter.OSS_DEFAULT_ADMIN.equals(request.getUserPrincipal().getName())) {
			map.put("funcs", funcManager.find("findSelect", Func.class, FuncVO.class, new PowerCommonQueryInfo(Status.ON)));
			map.put("menus", menuManager.find("findSelect", Menu.class, MenuVO.class, new PowerCommonQueryInfo(Status.ON)));
		} else {
			map.put("menus", menuManager.findByUsername(request.getUserPrincipal().getName()));
			map.put("funcs", funcManager.findByUsername(request.getUserPrincipal().getName()));
		}
		map.put("bean", userManager.getUserAndPower(id));
		map.put("applys", applyManager.find("findSelect", ApplyVO.class, new ApplyVO(Status.ON)));
		map.put("roles", roleManager.find("findSelect", RoleVO.class, new RoleVO(Status.ON)));
		return getViewName("view/user/allot");
	}

	/**
	 * 
	 * 授权
	 * 
	 * @param id 用户ID
	 * @param roles 角色IDS
	 * @param powers 权限IDS
	 * @see
	 */
	@RequestMapping(value = "allot/{id}", method = RequestMethod.POST)
	@ResponseBody
	public void allot(@PathVariable String id, String[] roles, String[] powers, String[] applys) {
		userManager.allot(id, roles, powers, applys);
	}

	/**
	 * 
	 * 
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "allotField/{id}/{username}", method = RequestMethod.GET)
	public String allotField(@PathVariable String id, @PathVariable String username, ModelMap map, HttpServletRequest request) {
		map.put("id", id);
		map.put("username", username);
		return getViewName("view/user/allotField");
	}

	/**
	 * 
	 * 
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "allotField/{userId}", method = RequestMethod.POST)
	@ResponseBody
	public void allotField(@PathVariable String userId, Long fieldId) {
		userManager.allotField(fieldId, userId);
	}
	
	/**
	 * 
	 * 
	 * @param id
	 * @param map
	 * @param request
	 * @return
	 * @see
	 */
	@RequestMapping(value = "deleteField/{userId}/{fieldId}", method = RequestMethod.GET)
	@ResponseBody
	public void deleteField(@PathVariable String userId, @PathVariable Long fieldId) {
		userManager.deleteField(fieldId, userId);
	}

	/**
	 * 
	 * 
	 * @return
	 * @see
	 */
	@RequestMapping(value = "findUserFieldPower/{username}", method = RequestMethod.POST)
	@ResponseBody
	public JQueryTableResult findUserFieldPower(@PathVariable String username, Paging paging) {
		return new JQueryTableResult(funcManager.findUserAllAttrPowers(username), paging);
	}

	/**
	 * 
	 * @param file
	 * @see
	 */
	@RequestMapping(value = "upload")
	public void upload(@RequestParam(value = "Filedata", required = false) MultipartFile file) {

	}

	/**
	 * 重置密码
	 * 
	 * @param id
	 * @param request
	 * @see
	 */
	@RequestMapping(value = "resetPassword/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void resetPassword(@PathVariable String id, HttpServletRequest request) {
		userManager.resetPassword(id, request.getUserPrincipal().getName());
	}

	/**
	 * @param userManager The userManager to set.
	 */
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

	/**
	 * @param menuManager The menuManager to set.
	 */
	public void setMenuManager(MenuManager menuManager) {
		this.menuManager = menuManager;
	}

	/**
	 * @param funcManager The funcManager to set.
	 */
	public void setFuncManager(FuncManager funcManager) {
		this.funcManager = funcManager;
	}

	/**
	 * @param roleManager The roleManager to set.
	 */
	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	/**
	 * @param applyManager The applyManager to set.
	 */
	public void setApplyManager(ApplyManager applyManager) {
		this.applyManager = applyManager;
	}

	/**
	 * @param userEhcacheHandle The userEhcacheHandle to set.
	 */
	public void setUserEhcacheHandle(UserEhcacheHandle userEhcacheHandle) {
		this.userEhcacheHandle = userEhcacheHandle;
	}

}
