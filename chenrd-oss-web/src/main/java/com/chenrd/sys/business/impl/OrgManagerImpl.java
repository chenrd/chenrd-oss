/*
 * 文件名：OrganizationManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月21日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.BeanCopyUtils;
import com.chenrd.dao.BaseDAO;
import com.chenrd.example.VO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.OrganizationManager;
import com.chenrd.sys.dao.OrganizationDAO;
import com.chenrd.sys.entity.Organization;
import com.chenrd.sys.vo.OrganizationVO;

/**
 * 
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author chenrd
 * @version 2015年5月21日
 * @see OrgManagerImpl
 * @since
 */
@Transactional
@Service("orgManager")
public class OrgManagerImpl extends AbstractPowerBusiness implements OrganizationManager {

	/**
	 * 
	 */
	@Resource(name = "orgDAO")
	private OrganizationDAO orgDAO;

	@Override
	public BaseDAO getDAO() {
		return orgDAO;
	}

	@Override
	public void saveOrUpdate(OrganizationVO vo) {
		Organization po = null, parent = (Organization) orgDAO.get(vo.getParentId());
		if (vo.getId() != null) {
			po = (Organization) orgDAO.get(vo.getId());
		} else {
			po = new Organization();
			po.setCreateTime(new Date());
			po.setCreateUser(vo.getCreator());
		}
		BeanCopyUtils.copy(vo, po, false);
		po.setKey(parent.getKey() + "/" + vo.getKey());
		po.setUpdateTime(new Date());
		po.setUpdateUser(vo.getCreator());
		orgDAO.saveOrUpdate(po);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends VO> T get(Serializable id, Class<T> clazz) {
		OrganizationVO vo = (OrganizationVO) super.get(id, clazz);
		int lastIndex = vo.getKey().lastIndexOf("/");
		if (lastIndex != -1)
			vo.setKey(vo.getKey().substring(lastIndex + 1));
		vo.setParentName(((Organization) orgDAO.get(vo.getParentId())).getName());
		return (T) vo;
	}

}
