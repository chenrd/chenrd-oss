package com.chenrd.app.business.impl;


import com.chenrd.app.business.ResourceManager;
import com.chenrd.app.dao.ResourceDAO;
import com.chenrd.app.entity.Resource;
import com.chenrd.dao.BaseDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * Created by chenrd on 2017/8/27.
 */
@Service("resourceManager")
public class ResourceManagerImpl extends AbstractPowerBusiness implements ResourceManager {

    @Autowired
    private ResourceDAO resourceDAO;

    @Value("#{settings['resource.path']}")
    private String path;

    @Override
    public Integer loadResource(MultipartFile file) {

        return null;
    }

    @Override
    public BaseDAO getDAO() {
        return resourceDAO;
    }
}
