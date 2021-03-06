/*
 * 文件名：AttributeManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月9日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.Paging;
import com.chenrd.dao.BeanUtil;
import com.chenrd.dao.QueryParamDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.AttributeManager;
import com.chenrd.sys.dao.AttributeDAO;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.service.Status;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 
 * 
 * @author chenrd
 * @version 2015年7月9日
 * @see AttributeManagerImpl
 * @since
 */
@Transactional
@Service("attributeManager")
public class AttributeManagerImpl extends AbstractPowerBusiness implements AttributeManager
{

    /**
     * 
     */
    @Resource(name = "attributeDAO")
    private AttributeDAO attributeDAO;
    
    @Override
    public List<AttributeVO> findChilds(String key)
    {
        return super.find("findChilds", Attribute.class, AttributeVO.class, new AttributeVO(key));
    }
    
    @Override
    public List<PowerInfo> findParent(String applyKey)
    {
        return BeanUtil.returnList(attributeDAO.findParent(applyKey), PowerInfo.class);
    }

    @Override
    public List<PowerInfo> find(String parentKey, Paging paging)
    {
        return BeanUtil.returnList(attributeDAO.findPaging(parentKey, paging), PowerInfo.class);
    }

    @Override
    public void publish(Long id)
    {
        Attribute po = attributeDAO.get(Attribute.class, id);
        po.setStatus(po.getStatus() == Status.OFF ? Status.NO : Status.OFF);
        attributeDAO.update(po);
    }

    @Override
    public void delete(Long id)
    {
        Attribute po = attributeDAO.get(Attribute.class, id);
        po.setStatus(Status.DELETED);
        attributeDAO.update(po);
    }

    @Override
    public QueryParamDAO getQueryParamsBaseDAO()
    {
        return attributeDAO;
    }

}
