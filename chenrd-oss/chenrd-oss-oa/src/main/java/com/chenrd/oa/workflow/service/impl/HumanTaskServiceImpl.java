/*
 * 文件名：HumanTaskServiceImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.drools.command.Context;
import org.drools.command.impl.GenericCommand;
import org.drools.command.impl.KnowledgeCommandContext;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.context.variable.VariableScopeInstance;
import org.jbpm.task.AccessType;
import org.jbpm.task.Status;
import org.jbpm.task.Task;
import org.jbpm.task.TaskService;
import org.jbpm.task.query.TaskSummary;
import org.jbpm.task.service.ContentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenrd.oa.workflow.service.HumanTaskService;
import com.chenrd.oa.workflow.service.KsessionService;

@Service("humanTaskService")
public class HumanTaskServiceImpl implements HumanTaskService {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private KsessionService ksessionService;
	
	/**
	 * 查询未完成的任务
	 */
	public List<TaskSummary> findTask(String userId) {
		List<TaskSummary> list = taskService.getTasksAssignedAsPotentialOwner(userId, "en-UK");
		return list;
	}
	
	/**
	 * 
	 * @param processId
	 */
	public List<TaskSummary> findTask(long processId, Status... status) {
		List<Status> list = new ArrayList<Status>();
		for (Status o: status) {
			list.add(o);
		}
		return taskService.getTasksByStatusByProcessId(processId, list, "en-UK");
	}
	
	/**
	 * 结束任务开始下一个任务
	 * @param taskId 任务id
	 * @param userId 任务完成用户id
	 * @param params 节点变量集合
	 * @param data 
	 * @throws Exception
	 */
	public void completeTask(final long taskId, String userId, Map<String, Object> params, Map<String, Object> data) throws Exception {
		Task task = taskService.getTask(taskId);
		
		this.setNodeVariable(params, task.getTaskData().getProcessInstanceId(), ksessionService.getStatefulKnowledgeSession());
		
		ContentData contentData = new ContentData();
		if (data != null) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = null;
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(data);
				contentData = new ContentData();
				contentData.setContent(bos.toByteArray());
				contentData.setAccessType(AccessType.Inline);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				out.close();
			}
		}
		
		taskService.complete( task.getId(), "krisv", contentData );
		
		/*final Map<String, Object> results = new HashMap<String, Object>();
		taskService.getWorkItemManager().completeWorkItem(task.getTaskData().getWorkItemId(), results);*/
	}
	
	/**
	 * 
	 */
	public void delegate(long taskId, String userId, String targetUserId) {
		taskService.delegate(taskId, userId, targetUserId);
	}
	
	/**
	 *  跳过当前任务
	 */
	public void skip(Long taskId, String userId){
		taskService.skip(taskId, userId);
	}
	
	/**
	 * 方法为设置节点变量
	 * @param params
	 */
	@SuppressWarnings({ "serial" })
	private Map<String, Object> setNodeVariable(final Map<String, Object> params, final long pId, StatefulKnowledgeSession ksession ) {
		return ksession.execute(new GenericCommand<Map<String, Object>>() {
		    public Map<String, Object> execute(Context context) {
		        StatefulKnowledgeSession ksession = ((KnowledgeCommandContext) context).getStatefulKnowledgesession();
		        org.jbpm.process.instance.ProcessInstance processInstance = (org.jbpm.process.instance.ProcessInstance) ksession.getProcessInstance(pId);
		        VariableScopeInstance variableScope = (VariableScopeInstance) processInstance.getContextInstance(VariableScope.VARIABLE_SCOPE);
		        for (String key : params.keySet()) {
		        	variableScope.setVariable(key, params.get(key));
		        }
		        Map<String, Object> variables = variableScope.getVariables();
		        return variables;
		    }
		});
	}
	
	/**
	 * 启动一个流程
	 * @throws Exception 
	 */
	public void startProcess(String processId, Map<String, Object> params) throws Exception {
		ksessionService.startProcess(processId, params);
	}
	
}
