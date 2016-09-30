/*
 * 文件名：DefLimitPower.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月5日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.ann;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 定义在字段上面，定义这个字段被限制了权限，
 * 那么需要到对应的ClassPower,FieldPower中找到自己的权限
 * 
 * @author chenrd
 * @version 2016年7月5日
 * @see LimitFieldPower
 * @since
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LimitFieldPower
{
    
    /**
     * 0:classKey
     * 1:fieldKey
     * 
     * @return 
     * @see
     */
    String[] value();
}
