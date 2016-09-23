/*
 * 文件名：PowerServiceImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月27日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.dao.BeanUtil;
import com.chenrd.sys.dao.PowerDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.service.info.PowerInfo;

/**
 * 
 * @author chenrd
 * @version 2015年5月27日
 * @see PowerServiceImpl
 * @since
 */
@Service("powerService")
public class PowerServiceImpl implements PowerService
{
    /**
     * 
     */
    @Resource(name = "powerDAO")
    private PowerDAO powerDAO;
    
    @Transactional
    @Override
    public void addFieldPower(PowerInfo info)
    {
        Attribute power = new Attribute();
        BeanUtils.copyProperties(info, power);
        power.setApply(powerDAO.getByProperties(Apply.class, "key", info.getApplyKey()));
        powerDAO.addPower(power);
    }

    /**
     * @param powerDAO The powerDAO to set.
     */
    public void setPowerDAO(PowerDAO powerDAO)
    {
        this.powerDAO = powerDAO;
    }

    @Transactional
    @Override
    public List<PowerInfo> findShiroFiler(String applyKey)
    {
        return BeanUtil.returnList(powerDAO.findFilter(applyKey), PowerInfo.class);
    }

    @Transactional
    @Override
    public String queryFullNameByUrl(String url)
    {
        List<Power> powers = powerDAO.findByProperty(Power.class, "url", url, null);
        return powers == null || powers.size() == 0 ? null : powers.get(0).getFullName();
    }

    @Transactional
    @Override
    public List<PowerInfo> findByApplyKeyUsername(String applyKey, String username, int powerType)
    {
        
        return null;
    }

}
