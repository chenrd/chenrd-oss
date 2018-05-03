package com.chenrd.app.business.impl;

import com.chenrd.app.business.NavbarManager;
import com.chenrd.app.dao.NavbarDAO;
import com.chenrd.app.entity.Navbar;
import com.chenrd.app.vo.NavbarVO;
import com.chenrd.dao.BaseDAO;
import com.chenrd.example.Domain;
import com.chenrd.example.Status;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by chenrd on 2017/8/30.
 */
@Service("navbarManager")
public class NavbarManagerImpl extends AbstractPowerBusiness implements NavbarManager {

    @Resource
    private NavbarDAO navbarDAO;

    @Override
    public void save(NavbarVO vo) {
        Navbar navbar = new Navbar();
        BeanUtils.copyProperties(vo, navbar);
        navbar.setStatus(Status.ON);
        navbar.setCreateTime(Calendar.getInstance().getTime());
        navbar.setUpdateTime(Calendar.getInstance().getTime());
        navbarDAO.save(navbar);
    }

    @Override
    public void update(NavbarVO vo) {
        Navbar navbar = new Navbar();
        BeanUtils.copyProperties(vo, navbar);
        navbar.setUpdateTime(Calendar.getInstance().getTime());
        navbarDAO.update(navbar);
    }

    @Transactional
    public void publish(Serializable id) {
        Domain d = getDAO().get(getDAO().getDomClass(), id);
        d.setStatus(Status.ON == d.getStatus() ? Status.OFF : Status.ON);
        getDAO().update(d);
    }

    @Override
    public BaseDAO getDAO() {
        return navbarDAO;
    }
}
