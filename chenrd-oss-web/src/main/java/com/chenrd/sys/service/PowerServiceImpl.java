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

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.dao.BeanUtil;
import com.chenrd.example.Status;
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
    
    @Transactional
    @Override
    public void saveFieldPowers(String applyKey, String defClassName, String defClassKey, String defFieldName, String defFieldKey, String[] values)
    {
        Apply apply = powerDAO.getByProperties(Apply.class, new String[] {"status", "key"}, new Object[] {Status.ON, applyKey});
        Attribute parent = powerDAO.getByProperties(Attribute.class, new String[] {"status", "name", "parentKey"}, new Object[] {Status.ON, defClassName, applyKey});
        if (parent == null) {
            parent = new Attribute();
            parent.setName(defClassName);
            parent.setParentKey(applyKey);
            parent.setApply(apply);
            parent.setCreateDate(new Date());
            
            parent.setKey(applyKey.concat("/").concat(PowerEnum.FILED.getKey() + "/").concat(defClassKey));
            parent.setFullName(applyKey + "-" + defClassName);
            parent.setStatus(1);
            parent.setAttrType(1);
            powerDAO.save(parent);
        }
        
        String key = parent.getKey();
        parent = powerDAO.getByProperties(Attribute.class, new String[] {"status", "name", "parentKey"}, new Object[] {Status.ON, defFieldName, key});
        if (parent == null) {
            parent = new Attribute();
            parent.setName(defFieldName);
            parent.setParentKey(key);
            parent.setApply(apply);
            parent.setCreateDate(new Date());
            
            parent.setKey(key.concat("/").concat(defFieldKey));
            parent.setFullName(applyKey + "-" + defClassName + "-" + defFieldName);
            parent.setStatus(1);
            parent.setAttrType(2);
            powerDAO.save(parent);
        }
        
        Attribute attribute = null;
        if (values != null) 
        {
            for (String value : values) {
                key = parent.getKey().concat("/").concat(value);
                if (powerDAO.countByProperty(Attribute.class, new String[] {"status", "key"}, new Object[] {Status.ON, key}) > 0) continue;
                attribute = new Attribute();
                attribute.setParentKey(parent.getKey());
                attribute.setApply(apply);
                attribute.setCreateDate(new Date());
                
                attribute.setKey(key);
                attribute.setFullName(parent.getFullName().concat("-").concat(value));
                attribute.setValue(value);
                attribute.setStatus(1);
                attribute.setAttrType(3);
                powerDAO.save(attribute);
            }
        }
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
