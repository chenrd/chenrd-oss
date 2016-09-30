/*
 * 文件名：PowerScan.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;

import com.chenrd.dao.annotation.ManyQueryParams;
import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.oss.power.ann.DefClassPower;
import com.chenrd.oss.power.ann.DefFieldPower;
import com.chenrd.oss.power.ann.LimitClassPower;
import com.chenrd.oss.power.ann.LimitFieldPower;

/**
 * 权限扫描实例
 * 
 * @author chenrd
 * @version 2016年7月4日
 * @see PowerScan
 * @since
 */
public class PowerScan
{
    
    private List<SessionFactory> sessionFactorys;
    
    private PowerCache powerCache = PowerCache.getPowerCache();
    
    private Map<String, DefPowerMetadata> classMetadata = new HashMap<String, DefPowerMetadata>();
    
    private String applyKey;
    
    
    
    /**
     * 
     *  
     * @throws ClassNotFoundException 
     * @see
     */
    @PostConstruct
    public void scan() throws ClassNotFoundException
    {
        for (SessionFactory sessionFactory : sessionFactorys)
        {
            Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
            Class<?> clazz = null;
            EntityQueryBuilder entityQuery = null;
            for (Entry<String, ClassMetadata> metadata : metadatas.entrySet())
            {
                clazz = Class.forName(metadata.getKey());
                DefClassPower classPower = clazz.getAnnotation(DefClassPower.class);
                if (classPower != null) 
                {
                    generateMetadata(clazz, classPower);
                }
                
                LimitClassPower limitClassPower = clazz.getAnnotation(LimitClassPower.class);
                
                if (limitClassPower != null)
                    entityQuery = PowerEntityQueryBuilder.newEntityQueryBuilder(classMetadata);
                else 
                    entityQuery = SimpleEntityQueryBuilder.newEntityQueryBuilder();
                
                entityQuery.with(clazz);
                powerCache.put(metadata.getKey(), entityQuery);
                generateCache(clazz, limitClassPower, entityQuery);
            }
        }
    }
    
    
    
    private void generateCache(Class<?> clazz, LimitClassPower limitClassPower, EntityQueryBuilder entityQuery)
    {
        if (clazz.getGenericSuperclass() != null)
            generateCache((Class<?>) clazz.getGenericSuperclass(), limitClassPower, entityQuery);
        for (Field field : clazz.getDeclaredFields())
        {
            if (Modifier.isStatic(field.getModifiers())) continue;
            if (entityQuery instanceof PowerEntityQueryBuilder) {
                LimitFieldPower limitFieldPower = field.getAnnotation(LimitFieldPower.class);
                if (limitFieldPower != null)
                    ((PowerEntityQueryBuilder) entityQuery).with(new LimitPowerMetadata(clazz.getName(), field.getName(), limitClassPower, limitFieldPower, DefPowerMetadata.formKeyName(applyKey, limitFieldPower.value()[0], limitFieldPower.value()[1])));
            } 
            
            QueryParams params = field.getAnnotation(QueryParams.class);
            if (params != null)
                entityQuery.with(new HqlAttribute(field.getName(), params));
            
            QueryOrder queryOrder = field.getAnnotation(QueryOrder.class);
            if (queryOrder != null)
                entityQuery.with(field.getName(), queryOrder);
            
            ManyQueryParams manyQueryParams = field.getAnnotation(ManyQueryParams.class);
            if (manyQueryParams != null)
                for (QueryParams queryParams : manyQueryParams.value())
                    entityQuery.with(new HqlAttribute(field.getName(), queryParams));
        }
    }
    
    /**
     * 
     * 
     * @param clazz
     * @param classPower 
     * @see
     */
    private void generateMetadata(Class<?> clazz, DefClassPower classPower)
    {
        DefPowerMetadata classPowerMetadata = null;
        for (Field field : clazz.getDeclaredFields())
        {
            if (!Modifier.isStatic(field.getModifiers()))
            {
                DefFieldPower defFieldPower = field.getAnnotation(DefFieldPower.class);
                if (defFieldPower == null)
                    continue;
                classPowerMetadata = new DefPowerMetadata(clazz, field, classPower, defFieldPower);
                classMetadata.put(DefPowerMetadata.formKeyName(applyKey, classPower.value()[1], defFieldPower.value()[1]), classPowerMetadata);
            }
        }
        if (clazz.getGenericSuperclass() != null)
            generateMetadata((Class<?>) clazz.getGenericSuperclass(), classPower);
    }



    /**
     * @return Returns the sessionFactorys.
     */
    public List<SessionFactory> getSessionFactorys()
    {
        return sessionFactorys;
    }

    /**
     * @param sessionFactorys The sessionFactorys to set.
     */
    public void setSessionFactorys(List<SessionFactory> sessionFactorys)
    {
        this.sessionFactorys = sessionFactorys;
    }

    /**
     * @param applyKey The applyKey to set.
     */
    public void setApplyKey(String applyKey)
    {
        this.applyKey = applyKey;
    }

}
