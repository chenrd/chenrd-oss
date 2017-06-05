/*
 * 文件名：ApplyManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.vo.ApplyVO;

/**
 * 应用业务逻辑处理器
 * 
 * @author chenrd
 * @version 2015年5月15日
 * @see ApplyManager
 * @since
 */
public interface ApplyManager extends PowerBusiness {

	/**
	 * 
	 * 添加或更新一个apply对象
	 * 
	 * @param apply
	 *            Apply
	 * @see
	 */
	void saveOrUpdate(ApplyVO apply);

}
