/*
 * 文件名：AbstractPowerBusiness.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power.abs;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.business.abs.AbstractBusiness;
import com.chenrd.common.Paging;
import com.chenrd.dao.BeanUtil;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Domain;
import com.chenrd.example.VO;
import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.oss.power.cache.EntityQueryBuilder;
import com.chenrd.oss.power.cache.PowerCache;
import com.chenrd.oss.power.cache.PowerEntityQueryBuilder;
import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.sys.service.info.UserInfo;


public abstract class AbstractPowerBusiness extends AbstractBusiness implements PowerBusiness
{
    
    private PowerCache powerCache = PowerCache.getPowerCache();
    
    @Autowired
    private UserEhcacheHandle userEhcacheHandle;
    
    @SuppressWarnings("unchecked")
    @Override
    public <D extends Domain, T extends VO> List<T> find(String methodName, Class<D> clas, Class<T> clazz, QueryInfo info, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, clas, info);
        String countHql = "select count(*) " + hql.toString();
        hql.insert(0, loadFindConstructor(clazz, methodName));
        return (List<T>) getDAO().findPaging(hql.toString(), countHql, params, paging);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <D extends Domain, T extends VO> List<T> find(String methodName, Class<D> clas, Class<T> clazz, QueryInfo info)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, clas, info);
        hql.insert(0, loadFindConstructor(clazz, methodName));
        return (List<T>) getDAO().find(hql.toString(), params);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends VO> List<T> find(String methodName, Class<T> clazz, QueryInfo info, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, getDAO().getDomClass(), info);
        String countHql = "select count(*) " + hql.toString();
        hql.insert(0, loadFindConstructor(clazz, methodName));
        return (List<T>) getDAO().findPaging(hql.toString(), countHql, params, paging);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends VO> List<T> find(String methodName, Class<T> clazz, QueryInfo info)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, getDAO().getDomClass(), info);
        hql.insert(0, loadFindConstructor(clazz, methodName));
        return (List<T>) getDAO().find(hql.toString(), params);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <D extends Domain, T extends VO> List<T> find(Class<D> clas, Class<T> clazz, QueryInfo info, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, clas, info);
        String countHql = "select count(*) " + hql.toString();
        List<? extends Domain> list = (List<? extends Domain>) getDAO().findPaging(hql.toString(), countHql, params, paging);
        return BeanUtil.returnList(list, clazz);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <D extends Domain, T extends VO> List<T> find(Class<D> clas, Class<T> clazz, QueryInfo info)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, clas, info);
        List<? extends Domain> list = (List<? extends Domain>) getDAO().find(hql.toString(), params);
        return BeanUtil.returnList(list, clazz);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends VO> List<T> find(Class<T> clazz, QueryInfo info, Paging paging)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, getDAO().getDomClass(), info);
        String countHql = "select count(*) " + hql.toString();
        List<? extends Domain> list = (List<? extends Domain>) getDAO().findPaging(hql.toString(), countHql, params, paging);
        return BeanUtil.returnList(list, clazz);
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public <T extends VO> List<T> find(Class<T> clazz, QueryInfo info)
    {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = appendHql(params, getDAO().getDomClass(), info);
        List<? extends Domain> list = (List<? extends Domain>) getDAO().find(hql.toString(), params);
        return BeanUtil.returnList(list, clazz);
    }
    
    private <E extends Domain, T extends VO> StringBuilder appendHql(Map<String, Serializable> params, Class<E> entityClass, QueryInfo info)
    {
        EntityQueryBuilder queryBuilder = powerCache.getLimitPowers(entityClass.getName());
        if (powerCache.isNotEmpty(entityClass.getName()) && queryBuilder instanceof PowerEntityQueryBuilder)
        {
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
            HttpServletRequest rq = attr.getRequest();
            UserInfo userInfo = userEhcacheHandle.get(rq.getRequestedSessionId());
            return ((PowerEntityQueryBuilder) queryBuilder).builder(info, params, userInfo.getAttributes());
        }
        return queryBuilder.builder(info, params);
    }
    
    private String loadFindConstructor(Class<?> clazz, String methodName)
    {
        String value = powerCache.getFindConstructor(clazz.getName() + "_" + methodName);
        if (StringUtils.isNotBlank(value)) return value;
        loadFindConstructor(clazz);
        value = powerCache.getFindConstructor(clazz.getName() + "_" + methodName);
        if (!StringUtils.isNotBlank(value)) throw new RuntimeException("");
        return value;
    }
    
    private void loadFindConstructor(Class<?> clazz)
    {
        FindConstructor findConstructor = null;
        for (Constructor<?> constructor : clazz.getConstructors())
        {
            findConstructor = constructor.getAnnotation(FindConstructor.class);
            if (findConstructor == null) continue;
            powerCache.putFindConstructor(clazz.getName() + "_" + findConstructor.name(), findConstructor.value());
        }
    }
}
