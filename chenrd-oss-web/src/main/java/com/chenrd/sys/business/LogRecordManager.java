/*
 * 文件名：LogRecordManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月18日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.sys.service.info.LogInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月18日
 * @see LogRecordManager
 * @since
 */
public interface LogRecordManager {
	/**
	 * 
	 * @param applyKey
	 * @param type
	 * @param paging
	 *            分页条件
	 * @return List<LogInfo>
	 * @see
	 */
	List<LogInfo> findPaging(String applyKey, int type, Paging paging);

	/**
	 * @param id
	 * @return 返回内容
	 */
	String getContent(Long id);
}
