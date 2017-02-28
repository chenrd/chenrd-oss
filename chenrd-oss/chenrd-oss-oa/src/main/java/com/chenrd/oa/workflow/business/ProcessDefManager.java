/*
 * 文件名：ProcessDefManager.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.business;

import org.springframework.web.multipart.MultipartFile;

import com.chenrd.oa.common.FullTextResultCode;
import com.chenrd.oa.workflow.vo.ProcessDefVO;
import com.chenrd.oss.power.PowerBusiness;

public interface ProcessDefManager extends PowerBusiness {
	
	FullTextResultCode saveOrUpdate(ProcessDefVO vo, MultipartFile file);
}
