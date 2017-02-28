/*
 * 文件名：ProcessDefManagerImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.business.impl;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chenrd.dao.BaseDAO;
import com.chenrd.example.Status;
import com.chenrd.oa.common.FullTextResultCode;
import com.chenrd.oa.workflow.business.ProcessDefManager;
import com.chenrd.oa.workflow.dao.ProcessDefDAO;
import com.chenrd.oa.workflow.entity.ProcessDef;
import com.chenrd.oa.workflow.vo.ProcessDefVO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;

@Service("processDefManager")
public class ProcessDefManagerImpl extends AbstractPowerBusiness implements ProcessDefManager {
	
	@Resource(name = "processDefDAO")
	private ProcessDefDAO processDefDAO;
	
	@Value("#{settings['process.file.path']}")
	private String processFilePath;
	
	private static Logger LOG = LoggerFactory.getLogger(ProcessDefManagerImpl.class);
	
	@Override
	public FullTextResultCode saveOrUpdate(ProcessDefVO vo, MultipartFile file) {
		String path = processFilePath + UUID.randomUUID().toString() + ".bpmn";
		if (file != null) {
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(path));
			} catch (IOException e) {
				LOG.error("上传流程文件失败：{}", e.getMessage(), e);
				return FullTextResultCode.process_upload_error;
			}
		}
		ProcessDef def = new ProcessDef();
		def.setName(vo.getName());
		def.setCreateTime(new Date());
		def.setCreator(vo.getCreator());
		def.setPath(path);
		
		if (vo.getId() != null) {
			ProcessDef old = (ProcessDef) processDefDAO.get(vo.getId());
			old.setStatus(Status.DELETED);
			def.setCode(old.getCode());
		} else {
			def.setCode(UUID.randomUUID().toString());
		}
		processDefDAO.save(def);
		return FullTextResultCode.success;
	}
	
	@Override
	public BaseDAO getDAO() {
		return processDefDAO;
	}

}
