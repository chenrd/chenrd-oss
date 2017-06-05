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

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;




import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.dao.info.QueryInfo;

public class PowerEntityQueryBuilder extends SimpleEntityQueryBuilder
{
    
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 5066237890580810743L;
    
    private Map<String, LimitPowerMetadata> limits;
    
    private Map<String, DefPowerMetadata> classMetadata;
    
    public StringBuilder builder(QueryInfo info, Map<String, Serializable> params, Map<String, List<String>> attrs) {
        for (Entry<String, LimitPowerMetadata> entry : limits.entrySet()) {
            if (entry.getValue().defPowerMetadata == null) {
                entry.getValue().defPowerMetadata = classMetadata.get(entry.getValue().key);
            }
            if (attrs.containsKey(entry.getKey())) {
                if (attrs.get(entry.getKey()) != null) params.put(entry.getValue().fieldName, attrs.get(entry.getKey()).toArray(new String[] {}));
            }
        }
        return super.builder(info, params);
    }
    
    public EntityQueryBuilder with(LimitPowerMetadata metadata) {
        if (limits == null) limits = new HashMap<String, LimitPowerMetadata>();
        if (metadata != null) {
            limits.put(metadata.key, metadata);
            if (!metadata.isFieldAnnotationQueryParams()) 
	            super.with(new HqlAttribute(metadata.fieldName, false, new QueryParams() {
	                @Override
	                public Class<? extends Annotation> annotationType() {
	                    return QueryParams.class;
	                }
	                @Override
	                public String value() {
	                    return "and";
	                }
	                @Override
	                public Nexus nexus() {
	                    return Nexus.IN;
	                }
	                @Override
	                public String bracket() {
	                    return "";
	                }
	                @Override
	                public String name() {
	                    return "";
	                }
	                @Override
	                public boolean defaultNotQuery() {
	                    return false;
	                }
	                @Override
	                public double defaultNotQueryValue() {
	                    return 0;
	                }
					@Override
					public String hql() {
						return "";
					}
	            }));
        }
        return this;
    }
    
    public static EntityQueryBuilder newEntityQueryBuilder(Map<String, DefPowerMetadata> classMetadata)
    {
        return new PowerEntityQueryBuilder(classMetadata);
    }
    
    private PowerEntityQueryBuilder(Map<String, DefPowerMetadata> classMetadata)
    {
        this.classMetadata = classMetadata;
    }
}
