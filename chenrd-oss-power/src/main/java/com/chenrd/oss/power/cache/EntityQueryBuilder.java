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

/**
 * 实体类的自动化构造查询语句
 * 自动化构造查询语句的入口类，目的是为了简化开发，重复写一些类似的查询代码，省下大量的重复时间
 * 写在最前面，这个接口的目的是自动构造一些简单的查询语句，单实体查询（注意这里是单实体，不是单表），复杂的sql还是要在代码中自己构造
 * 
 * @author chenrd
 * @version 2017年4月10日
 * @see EntityQueryBuilder
 * @since
 */
public interface EntityQueryBuilder extends Serializable {
    
    EntityQueryBuilder with(Class<?> clazz);
    
    EntityQueryBuilder with(HqlAttribute hqlAttribute);
    
    EntityQueryBuilder with(String fieldName, QueryOrder order);
    
    /**
     * 构造一个查询的hql语句，
     * 
     * @param info {@link QueryInfo} 必须实现这个接口，一个重要的原则，一个EntityQueryBuilder实例，代表一个实体的简化构造查询，不可能被其他的实体构造，还有一重要原则，查询的QueryInfo参数必须始终都是同一个实现类
     * @param params 这个参数最后会被Query.setProperties(Map)方法所使用，PowerEntityQueryBuilder在实现这个方法的时候会，先在params里面加入限制查询条件，所以这个类必须是final的
     * @return 
     * @see
     */
    StringBuilder builder(QueryInfo info, Map<String, Serializable> params);
    
}
