/*
 * 文件名：AttributePowerDefinedService.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年8月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.service;

import java.util.List;

import com.chenrd.oss.power.AttributeDefined;

public interface AttributePowerDefinedService
{
    
    /**
     * 获取系统所有的字段权限定义
     * 
     * @return 
     * @see
     */
    List<AttributeDefined> systemAllAttributeDefined();
}
