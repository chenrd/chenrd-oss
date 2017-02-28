/*
 * 文件名：WorkflowInti.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.init;

import java.util.List;

import org.jbpm.task.Group;
import org.jbpm.task.User;
import org.jbpm.task.service.TaskService;
import org.jbpm.task.service.TaskServiceSession;

import com.chenrd.sys.service.OrganizationService;
import com.chenrd.sys.service.UserService;
import com.chenrd.sys.service.info.OrganizationInfo;
import com.chenrd.sys.service.info.UserInfo;

public class WorkflowInti {
	
	private TaskService internalTaskService;
	
	private UserService userService;
	
	private OrganizationService organizationService;
	
	public void init() {
		TaskServiceSession taskSession = internalTaskService.createSession();
		List<UserInfo> users = userService.findSelect();
		List<OrganizationInfo> orgs = organizationService.findSelect();
		for (UserInfo u : users) {
			taskSession.addUser(new User(u.getUsername()));
		}
		for (OrganizationInfo o : orgs) {
			taskSession.addGroup(new Group(o.getKey()));
		}
	}

	/**
	 * @param internalTaskService The internalTaskService to set.
	 */
	public void setInternalTaskService(TaskService internalTaskService) {
		this.internalTaskService = internalTaskService;
	}

	/**
	 * @param userService The userService to set.
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * @param organizationService The organizationService to set.
	 */
	public void setOrganizationService(OrganizationService organizationService) {
		this.organizationService = organizationService;
	}
}
