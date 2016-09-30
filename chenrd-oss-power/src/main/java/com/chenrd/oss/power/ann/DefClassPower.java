/*
 * 文件名：AttributePower.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月4日
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
 * 定义一个ClassPower权限
 * 相当于一个类别，value就是类别的名称
 * 
 * @author chenrd
 * @version 2016年7月5日
 * @see DefClassPower
 * @since
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefClassPower
{
    /**
     * value[0] name, value[1] key
     * 
     * @return 
     * @see
     */
    String[] value() default "";
}
