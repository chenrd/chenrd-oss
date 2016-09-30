/*
 * 文件名：ClassPowerMetadata.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月5日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import java.lang.reflect.Field;

import com.chenrd.oss.power.ann.DefClassPower;
import com.chenrd.oss.power.ann.DefFieldPower;
import com.chenrd.sys.service.PowerEnum;

/**
 * 
 * 
 * @author chenrd
 * @version 2016年7月5日
 * @see DefPowerMetadata
 * @since
 */
public class DefPowerMetadata
{
    
    Class<?> father;
    
    Field field;
    
    DefClassPower classPower;
    
    DefFieldPower fieldPower;

    /**
     * @param father
     * @param field
     * @param classPower
     * @param fieldPower
     */
    public DefPowerMetadata(Class<?> father, Field field, DefClassPower classPower,
                              DefFieldPower fieldPower)
    {
        super();
        this.father = father;
        this.field = field;
        this.classPower = classPower;
        this.fieldPower = fieldPower;
    }
    
    public static String formKeyName(String applyKey, String classKey, String fieldKey)
    {
        return applyKey.concat("/").concat(PowerEnum.FILED.getKey() + "/").concat(classKey).concat("/").concat(fieldKey);
    }
    
}
