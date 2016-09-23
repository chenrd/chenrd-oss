/*
 * 文件名：PowerEntityQueryBuilder.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月7日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.sys.service.info.UserInfo;

public class PowerEntityQueryBuilder extends SimpleEntityQueryBuilder
{
    
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 5066237890580810743L;
    
    private List<LimitPowerMetadata> limits;
    
    public EntityQueryBuilder with(LimitPowerMetadata metadata)
    {
        if (limits == null) limits = new ArrayList<LimitPowerMetadata>();
        if (metadata != null) {
            limits.add(metadata);
            super.with(new HqlAttribute(metadata.fieldName, new QueryParams()
            {
                @Override
                public Class<? extends Annotation> annotationType()
                {
                    return QueryParams.class;
                }
                @Override
                public String value()
                {
                    return "and";
                }
                @Override
                public Nexus nexus()
                {
                    return Nexus.IN;
                }
                @Override
                public String bracket()
                {
                    return "";
                }
                @Override
                public String name()
                {
                    return "";
                }
                @Override
                public boolean defaultNotQuery()
                {
                    return false;
                }
                @Override
                public double defaultNotQueryValue()
                {
                    return 0;
                }
            }));
        }
        return this;
    }
    
    public void configQueryInfo(QueryInfo queryInfo, UserInfo userInfo)
    {
        for (LimitPowerMetadata metadata : limits)
        {
            String key = DefPowerMetadata.formKeyName(metadata.limitFieldPower.defClassName(), metadata.limitFieldPower.defFieldName());
            if (userInfo.attributesContainsKey(key)) continue;
            
        }
    }
    
    public static EntityQueryBuilder newEntityQueryBuilder()
    {
        return new PowerEntityQueryBuilder();
    }
    
    private PowerEntityQueryBuilder()
    {
        
    }
}
