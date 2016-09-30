/*
 * 文件名：RoleManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.dao.BeanUtil;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.RoleManager;
import com.chenrd.sys.dao.RoleDAO;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.entity.Role;
import com.chenrd.sys.service.Status;
import com.chenrd.sys.service.info.RoleInfo;

/**
 * 
 * 角色管理器
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleManagerImpl
 * @since
 */
@Transactional
@Service("roleManager")
public class RoleManagerImpl extends AbstractPowerBusiness implements RoleManager
{
    /**
     * 
     */
    @Resource(name = "roleDAO")
    private RoleDAO roleDAO;
    
    @Override
    public List<RoleInfo> findPaging(String name, Paging paging)
    {
        return BeanUtil.returnList(roleDAO.findPaging(name, paging), RoleInfo.class);
    }

    /**
     * @param roleDAO The roleDAO to set.
     */
    public void setRoleDAO(RoleDAO roleDAO)
    {
        this.roleDAO = roleDAO;
    }

    @Override
    public void saveOrUpdate(RoleInfo info, Long[] powers)
    {
        Role role = null;
        if (info.getId() != null)
        {
            role = roleDAO.get(Role.class, info.getId());
            info.setStatus(role.getStatus());
        }
        else
        {
            role = new Role();
            role.setCreateDate(new Date());
        }
        BeanUtil.copyProperties(info, role);
        if (powers != null)
        {
            Set<Power> set = new HashSet<Power>();
            for (Long power : powers)
            {
                set.add(roleDAO.get(Power.class, power));
            }
            role.setPowers(set);
        }
        role.setUpdateDate(new Date());
        roleDAO.saveOrUpdate(role);
    }

    @Override
    public void publish(Long id)
    {
        Role role = roleDAO.get(Role.class, id);
        if (role == null)
        {
            throw new RuntimeException("发布失败，没有找到要发布的角色");
        }
        role.setStatus(role.getStatus() == 0 ? 1 : 0);
        roleDAO.update(role);
    }

    @Override
    public void deleted(Long id)
    {
        Role role = roleDAO.get(Role.class, id);
        if (role == null)
        {
            throw new RuntimeException("删除失败，没有找到要删除的角色");
        }
        role.setStatus(-1);
        roleDAO.update(role);
    }

    @Override
    public RoleInfo get(Long id)
    {
        Role role = roleDAO.get(Role.class, id);
        if (role == null)
        {
            return null;
        }
        RoleInfo info = new RoleInfo();
        BeanUtil.copyProperties(role, info);
        String powers = "";
        for (Power power : role.getPowers())
        {
            powers += power.getId() + ",";
        }
        info.setPowers(powers);
        return info;
    }

    @Override
    public List<RoleInfo> findALL()
    {
        return BeanUtil.returnList(roleDAO.findByProperty(Role.class, "status", Status.NO, null), RoleInfo.class);
    }

    @Override
    public BaseDAO getDAO()
    {
        return roleDAO;
    }

}
