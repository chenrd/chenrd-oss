/*
 * 文件名：EntityQueryBuilder.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月7日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import java.io.Serializable;
import java.util.Map;

import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.dao.info.QueryInfo;

public interface EntityQueryBuilder extends Serializable
{
    
    EntityQueryBuilder with(Class<?> clazz);
    
    EntityQueryBuilder with(HqlAttribute hqlAttribute);
    
    EntityQueryBuilder with(String fieldName, QueryOrder order);
    
    StringBuilder builder(QueryInfo info, Map<String, Serializable> params);
    
}
