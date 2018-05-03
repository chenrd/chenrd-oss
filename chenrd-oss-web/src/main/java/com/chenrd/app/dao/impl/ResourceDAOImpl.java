package com.chenrd.app.dao.impl;

import com.chenrd.app.dao.ResourceDAO;
import com.chenrd.app.entity.Resource;
import com.chenrd.dao.abs.AbstractBaseDAO;
import org.springframework.stereotype.Repository;

/**
 * Created by chenrd on 2017/8/27.
 */
@Repository("resourceDAO")
public class ResourceDAOImpl extends AbstractBaseDAO<Resource> implements ResourceDAO {
}
