/*
 * 文件名：ApplyManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.BeanCopyUtils;
import com.chenrd.dao.BaseDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.ApplyManager;
import com.chenrd.sys.dao.ApplyDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.vo.ApplyVO;

/**
 * 
 * @author chenrd
 * @version 2015年5月15日
 * @see ApplyManagerImpl
 * @since
 */
@Transactional
@Service("applyManager")
public class ApplyManagerImpl extends AbstractPowerBusiness implements ApplyManager {
	/**
	 * 
	 */
	@Resource(name = "applyDAO")
	private ApplyDAO applyDAO;

	@Override
	public void saveOrUpdate(ApplyVO apply) {
		Apply po = null;
		if (apply.getId() != null)
			po = applyDAO.get(Apply.class, apply.getId());
		else
			po = new Apply();
		BeanCopyUtils.copy(apply, po, true);
		applyDAO.saveOrUpdate(po);
	}

	@Override
	public BaseDAO getDAO() {
		return applyDAO;
	}

}
