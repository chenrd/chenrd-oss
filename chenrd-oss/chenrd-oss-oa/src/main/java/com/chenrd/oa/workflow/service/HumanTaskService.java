/*
 * 文件名：HumanTaskService.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.service;

import java.util.List;
import java.util.Map;

import org.jbpm.task.Status;
import org.jbpm.task.query.TaskSummary;

public interface HumanTaskService {
	
	/**
	 * 查询用户的任务
	 * @param userId
	 */
	List<TaskSummary> findTask(String userId);
	
	List<TaskSummary> findTask(long processId, Status... status);
	
	/**
	 * 结束任务开始下一个任务
	 * @param taskId 任务id
	 * @param userId 任务完成用户id
	 * @param params 节点变量集合
	 * @param data 
	 * @throws Exception
	 */
	void completeTask(final long taskId, String userId, Map<String, Object> params, Map<String, Object> data) throws Exception;

	/**
	 * 把任务授权给其他人处理
	 * @param taskId 
	 * @see
	 */
	void delegate(long taskId, String userId, String targetUserId);
	
	/**
	 * 提过当前任务
	 * @see
	 */
	void skip(Long taskId, String userId);
}
