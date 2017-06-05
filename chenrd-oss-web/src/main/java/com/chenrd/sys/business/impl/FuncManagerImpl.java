/*
 * 文件名：FuncManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.BeanCopyUtils;
import com.chenrd.dao.BaseDAO;
import com.chenrd.dao.BeanUtil;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.FuncManager;
import com.chenrd.sys.dao.PowerDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Func;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.service.PowerEnum;
import com.chenrd.sys.service.PowerType;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 功能manager
 * 
 * @author chenrd
 * @version 2015年6月16日
 * @see FuncManagerImpl
 * @since
 */
@Transactional
@Service("funcManager")
public class FuncManagerImpl extends AbstractPowerBusiness implements FuncManager {

	/**
	 * 
	 */
	@Resource(name = "powerDAO")
	private PowerDAO powerDAO;

	@Override
	public PowerInfo getByKey(String key) {
		Power power = powerDAO.getByProperties(Power.class, "key", key);
		if (power == null) {
			return null;
		}
		PowerInfo powerInfo = new PowerInfo();
		BeanUtil.copyProperties(power, powerInfo);
		return powerInfo;
	}

	@Override
	public PowerInfo get(Long id) {
		Power power = powerDAO.get(Power.class, id);
		if (power == null) {
			return null;
		}
		PowerInfo powerInfo = new PowerInfo();
		BeanUtil.copyProperties(power, powerInfo);
		Menu menu = powerDAO.getByProperties(Menu.class, "key", powerInfo.getParentKey());
		powerInfo.setUrl(powerInfo.getUrl().replace(menu.getUrl(), ""));
		powerInfo.setKey(powerInfo.getKey()
				.replace(power.getApply().getKey() + "/" + PowerEnum.FUNC.getKey()
						+ powerInfo.getParentKey().substring(powerInfo.getParentKey().indexOf("/"))
								.substring(powerInfo.getParentKey().indexOf("/") + 1)
						+ "/", ""));
		return powerInfo;
	}

	@Override
	public void saveOrUpdate(PowerInfo powerInfo) {
		Func func = null;
		if (powerInfo.getId() != null) {
			func = powerDAO.get(Func.class, powerInfo.getId());
			powerInfo.setStatus(func.getStatus());
		} else {
			func = new Func();
		}
		BeanUtil.copyProperties(powerInfo, func);
		func.setApply(powerDAO.get(Apply.class, powerInfo.getApplyId()));
		String str = "";
		func.setKey(func.getApply().getKey() + "/" + PowerEnum.FUNC.getKey()
				+ (str = powerInfo.getParentKey().substring(powerInfo.getParentKey().indexOf("/") + 1))
						.substring(str.indexOf("/"))
				+ "/" + powerInfo.getKey());
		// 全称
		Menu parent = powerDAO.getByProperties(Menu.class, "key", powerInfo.getParentKey());
		func.setFullName(parent.getFullName() + "-" + func.getName());
		func.setUrl(parent.getUrl() + powerInfo.getUrl());
		func.setType(PowerType.FUNC_POWER);
		powerDAO.saveOrUpdate(func);
	}


	@Override
	public List<PowerInfo> findByUsername(String username) {
		// 获取与角色相关的菜单权限
		List<Power> menus = powerDAO.findRoleFuncByUsername(username);
		// 获取直接关联用户的权限
		List<Power> ms = powerDAO.findUserFuncByUsername(username);

		if (menus == null) {
			menus = new ArrayList<Power>();
		}
		if (ms != null) {
			for (Power menu : ms) {
				if (!menus.contains(menu)) {
					menus.add(menu);
				}
			}
		}

		final Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
		// 排序定义
		Comparator<Power> comparator = new Comparator<Power>() {
			@Override
			public int compare(Power o1, Power o2) {
				if (cmp.compare(o1.getKey(), o2.getKey()) > 0) {
					return 1;
				} else if (cmp.compare(o1.getKey(), o2.getKey()) < 0) {
					return -1;
				}
				return 0;
			}
		};
		// 开始排序
		Collections.sort(menus, comparator);
		return BeanUtil.returnList(menus, PowerInfo.class);

	}

	@Override
	public List<AttributeVO> findUserAllAttrPowers(String username) {
		List<Attribute> attributes = powerDAO.findUserFieldPower(username);
		List<AttributeVO> vos = new ArrayList<AttributeVO>();
		AttributeVO vo = null;
		for (Attribute form : attributes) {
			vo = new AttributeVO();
			BeanCopyUtils.copy(form, vo, false);
			vo.setApplyName(form.getApply().getName());
			vos.add(vo);
		}
		return vos;
	}

	@Override
	public BaseDAO getDAO() {
		return powerDAO;
	}

}
