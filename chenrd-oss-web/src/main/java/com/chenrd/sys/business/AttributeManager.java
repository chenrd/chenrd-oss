/*
 * 文件名：AttributeManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 
 * @author chenrd
 * @version 2015年7月9日
 * @see AttributeManager
 * @since
 */
public interface AttributeManager extends PowerBusiness {

	/**
	 * 
	 * 
	 * @param key
	 * @return
	 * @see
	 */
	List<AttributeVO> findChilds(String key);

	/**
	 * 二级目录
	 * 
	 * @param applyKey
	 *            应用KEY
	 * @return
	 * @see
	 */
	List<PowerInfo> findParent(String applyKey);

	void saveOrUpdate(AttributeVO vo);

	/**
	 * 
	 * 
	 * @param parentKey
	 *            标识
	 * @param 分页条件
	 */
	List<PowerInfo> find(String parentKey, Paging paging);

	/**
	 * 启用
	 * 
	 * @param id
	 * @see
	 */
	void publish(Long id);

	/**
	 * 删除数据
	 * 
	 * @param id
	 * @see
	 */
	void delete(Long id);

}
