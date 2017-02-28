/*
 * 文件名：OrganizationServiceImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月17日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.chenrd.dao.BaseDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.dao.OrganizationDAO;
import com.chenrd.sys.service.info.OrganizationInfo;
import com.chenrd.sys.vo.OrganizationVO;

@Service(value = "organizationService")
public class OrganizationServiceImpl extends AbstractPowerBusiness implements OrganizationService {
	
	@Resource(name = "orgDAO")
	private OrganizationDAO orgDAO;
	
	@Override
	public List<OrganizationInfo> findSelect() {
		List<OrganizationVO> vos = super.find("findSelectByService", OrganizationVO.class, OrganizationVO.QUERY_YES_PUBLISH);
		List<OrganizationInfo> infos = new ArrayList<OrganizationInfo>();
		OrganizationInfo info = null;
		if (vos != null && vos.size() > 0) {
			for (OrganizationVO vo : vos) {
				info = new OrganizationInfo();
				info.setId(vo.getId());
				info.setName(vo.getName());
				info.setFullName(vo.getFullName());
				info.setParentId(vo.getParentId());
				info.setParentName(vo.getParentName());
				info.setLeaderId(vo.getLeaderId());
				info.setLeaderName(vo.getLeaderName());
				info.setKey(vo.getKey());
				infos.add(info);
			}
		}
		return infos;
	}

	@Override
	public BaseDAO getDAO() {
		return orgDAO;
	}
}
