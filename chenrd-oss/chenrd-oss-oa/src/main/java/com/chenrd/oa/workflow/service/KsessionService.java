/*
 * 文件名：MyKSessionService.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.service;

import java.io.InputStream;
import java.util.Map;

import org.drools.runtime.StatefulKnowledgeSession;

public interface KsessionService {
	
	StatefulKnowledgeSession getStatefulKnowledgeSession();
	
	/**
	 * 添加资源 bpmn2文件
	 * @param resource 
	 * @see
	 */
	void addResource(InputStream stream);
	
	/**
	 * 启动一个流程
	 * @param processId
	 * @param params 
	 * @see
	 */
	void startProcess(String processId, Map<String, Object> params);
	
}
