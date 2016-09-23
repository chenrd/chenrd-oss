/*
 * 文件名：Power.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.chenrd.sys.service.PowerEnum;



/**
 * 
 * 其它应用权限管理
 * @author chenrd
 * @version 2015年5月12日
 * @see Power
 * @since
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Power
{
    
    /**
     * 对应权限name
     * @return 
     * @see
     */
    String name();
    
    /**
     * 
     * 默认字段权限
     * @return 
     * @see
     */
    PowerEnum type() default PowerEnum.FILED;
    
    /**
     * 
     * 对应权限KEY
     * @return 
     * @see
     */
    String key();
    
    /**
     * 对应属性
     * @return 
     * @see
     */
    String[] value();
}
