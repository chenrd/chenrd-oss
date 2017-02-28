/*
 * 文件名：KsessionServiceImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.transaction.Transaction;

import org.apache.commons.io.FileUtils;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.process.workitem.wsht.LocalHTWorkItemHandler;
import org.jbpm.task.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.chenrd.oa.workflow.dao.ProcessDefDAO;
import com.chenrd.oa.workflow.entity.ProcessDef;
import com.chenrd.oa.workflow.service.KsessionService;

@Service("ksessionService")
public class KsessionServiceImpl implements KsessionService {
	
	private KnowledgeBuilder kbuilder;
	
	@Resource(name = "processDefDAO")
	private ProcessDefDAO processDefDAO;
	
	private StatefulKnowledgeSession ksession;
	
	@Autowired
	private TaskService taskService;
	
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	
	private static Logger LOG = LoggerFactory.getLogger(KsessionServiceImpl.class);
	
	@Autowired
	private JtaTransactionManager transactionManager;
	
	@PostConstruct
	public void init() {
		kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		try {
			Transaction transaction = transactionManager.createTransaction("name", 1000);
			List<ProcessDef> list = processDefDAO.findQueryName("queryInitoLoadDef", null);
			if (list != null) {
				try {
					for (ProcessDef def : list) {
						kbuilder.add(ResourceFactory.newInputStreamResource(FileUtils.openInputStream(new File(def.getPath())), "utf-8"), ResourceType.BPMN2);
					}
					ksession = kbuilder.newKnowledgeBase().newStatefulKnowledgeSession();
				} catch (IOException e) {
					LOG.error("初始化加载流程文件错误：{}", e.getMessage());
				}
			}
			transaction.commit();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private StatefulKnowledgeSession getStatefulKnowledgeSession(boolean use) {
		if (use) {
			lock.writeLock().lock();
			if (ksession != null) ksession.dispose();
			ksession = kbuilder.newKnowledgeBase().newStatefulKnowledgeSession();
			LocalHTWorkItemHandler localHTWorkItemHandler = new LocalHTWorkItemHandler(taskService, ksession);
			localHTWorkItemHandler.setLocal(true);
			ksession.getWorkItemManager().registerWorkItemHandler("Human Task", localHTWorkItemHandler);
			lock.writeLock().unlock();
		}
		try {
			lock.readLock().lock();
			return ksession;
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public StatefulKnowledgeSession getStatefulKnowledgeSession() {
		return getStatefulKnowledgeSession(false);
	}

	@Override
	public void addResource(InputStream stream) {
		kbuilder.add(ResourceFactory.newInputStreamResource(stream, "utf-8"), ResourceType.BPMN2);
		getStatefulKnowledgeSession(true);
	}

	@Override
	public void startProcess(String processId, Map<String, Object> params) {
		getStatefulKnowledgeSession(false).startProcess(processId, params);
	}

}
