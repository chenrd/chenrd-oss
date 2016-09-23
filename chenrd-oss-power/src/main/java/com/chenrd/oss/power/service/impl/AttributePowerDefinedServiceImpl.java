/*
 * 文件名：AttributePowerDefinedServiceImpl.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年8月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.service.impl;

import java.util.List;

import com.chenrd.oss.power.AttributeDefined;
import com.chenrd.oss.power.cache.PowerCache;
import com.chenrd.oss.power.service.AttributePowerDefinedService;

public class AttributePowerDefinedServiceImpl implements AttributePowerDefinedService
{
    
    private PowerCache powerCache = PowerCache.getPowerCache();

    @Override
    public List<AttributeDefined> systemAllAttributeDefined()
    {
        
        
        return null;
    }
    
}
