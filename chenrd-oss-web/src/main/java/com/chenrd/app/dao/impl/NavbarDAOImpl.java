package com.chenrd.app.dao.impl;

import com.chenrd.app.dao.NavbarDAO;
import com.chenrd.app.entity.Navbar;
import com.chenrd.dao.abs.AbstractBaseDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by chenrd on 2017/8/30.
 */
@Repository("navbarDAO")
public class NavbarDAOImpl extends AbstractBaseDAO<Navbar> implements NavbarDAO {
}
