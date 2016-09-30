/*
 * 文件名：LimitPowerMetadata.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import com.chenrd.oss.power.ann.LimitClassPower;
import com.chenrd.oss.power.ann.LimitFieldPower;

public class LimitPowerMetadata
{
    String className;
    
    String fieldName;
    
    LimitClassPower limitClassPower;
    
    LimitFieldPower limitFieldPower;
    
    DefPowerMetadata defPowerMetadata;
    
    String key;
    
    /**
     * @param className
     * @param fieldName
     * @param limitClassPower
     * @param limitFieldPower
     * @param defPowerMetadata
     */
    public LimitPowerMetadata(String className, String fieldName, LimitClassPower limitClassPower,
                              LimitFieldPower limitFieldPower, String key)
    {
        super();
        this.className = className;
        this.fieldName = fieldName;
        this.limitClassPower = limitClassPower;
        this.limitFieldPower = limitFieldPower;
        this.key = key;
    }
    
}
