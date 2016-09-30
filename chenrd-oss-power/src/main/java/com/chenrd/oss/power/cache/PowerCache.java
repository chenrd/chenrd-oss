/*
 * 文件名：PowerCache.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.cache;

import org.ehcache.Cache;
import org.ehcache.config.units.EntryUnit;

import com.chenrd.ehcache.EhcacheFactory;

/**
 * key为className
 * value:List<LimitPowerMetadata>
 * 
 * @author chenrd
 * @version 2016年7月6日
 * @see PowerCache
 * @since
 */
public class PowerCache
{
    
    private static final String CACHE_NAME = "ATTRIBUTE_POWER_CACHE";
    
    private static final String find_Constructors = "ATTRIBUTE_POWER_FIND_CONSTRUCTORS_HQL";
    
    private static PowerCache powerCache;
    
    private EhcacheFactory cacheFactory = EhcacheFactory.getEhcacheFactory();
    
    private int heapSize = 100;
    
    private int fcHeapSize = 100;
    
    private Cache<String, EntityQueryBuilder> cache = cacheFactory.newCache(cacheFactory.getExampleBuilder().newBaseCacheExample(CACHE_NAME, String.class, EntityQueryBuilder.class).withHeap(heapSize, EntryUnit.ENTRIES));
    
    private Cache<String, String> findConstructors = cacheFactory.newCache(cacheFactory.getExampleBuilder().newBaseCacheExample(find_Constructors, String.class, String.class).withHeap(fcHeapSize, EntryUnit.ENTRIES));
    
    
    private PowerCache()
    {
        
    }
    
    public void putFindConstructor(String key, String hql)
    {
        findConstructors.put(key, hql);
    }
    
    public String getFindConstructor(String key)
    {
        return findConstructors.get(key);
    }
    
    public static PowerCache getPowerCache()
    {
        if (powerCache == null)
            powerCache = new PowerCache();
        return powerCache;
    }
    
    public boolean isNotEmpty(String key)
    {
        return cache.containsKey(key);
    }
    
    public boolean isLimitEntity(String key)
    {
        return isNotEmpty(key) && powerCache.getLimitPowers(key) instanceof PowerEntityQueryBuilder;
    }
    
    public EntityQueryBuilder getLimitPowers(String key)
    {
        return cache.get(key);
    }
    
    public void put(String key, EntityQueryBuilder value)
    {
        cache.put(key, value);
    }

    /**
     * @param heapSize The heapSize to set.
     */
    public void setHeapSize(int heapSize)
    {
        this.heapSize = heapSize;
    }

    /**
     * @param fcHeapSize The fcHeapSize to set.
     */
    public void setFcHeapSize(int fcHeapSize)
    {
        this.fcHeapSize = fcHeapSize;
    }
    
}
