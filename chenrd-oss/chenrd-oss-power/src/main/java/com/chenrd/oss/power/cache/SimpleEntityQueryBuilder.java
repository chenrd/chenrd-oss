/*
 * 文件名：SimpleEntityQueryBuilder.java
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
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chenrd.common.DateUtil;
import com.chenrd.common.ocp.DateFormat;
import com.chenrd.dao.BuilderHqlException;
import com.chenrd.dao.annotation.QueryOrder;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.dao.info.HqlOrderAttribute;
import com.chenrd.dao.info.QueryInfo;

public class SimpleEntityQueryBuilder implements EntityQueryBuilder
{
    
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 2511829826226040290L;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleEntityQueryBuilder.class);
    
    private Class<?> clazz;
    
    private List<HqlAttribute> list = new ArrayList<HqlAttribute>();
    
    private List<HqlOrderAttribute> orders = new ArrayList<HqlOrderAttribute>();
    
    private String orderHQL;
    
    public static final String leftBrecket = "(", rightBrecket = ")", get_method_prefix = "get", is_method_prefix = "", empty_string = "", emtyp_interval_string = " ";
    
    public static EntityQueryBuilder newEntityQueryBuilder()
    {
        return new SimpleEntityQueryBuilder();
    }
    
    public EntityQueryBuilder with(Class<?> clazz)
    {
        this.clazz = clazz;
        return this;
    }
    
    @Override
    public EntityQueryBuilder with(HqlAttribute hqlAttribute)
    {
        list.add(hqlAttribute);
        return this;
    }

    @Override
    public EntityQueryBuilder with(String fieldName, QueryOrder order)
    {
        orders.add(new HqlOrderAttribute(fieldName, order.index(), order.value()));
        return this;
    }

    @Override
    public StringBuilder builder(QueryInfo info, Map<String, Serializable> params)
    {
        StringBuilder hql = new StringBuilder("from ").append(clazz.getSimpleName()).append(" as po where 1=1");
        Object valueTmp = null;
        if (info != null) for (HqlAttribute attribute : list)
        {
            String fieldName = StringUtils.isNotBlank(attribute.getParams().name()) ? attribute.getParams().name() : attribute.getFieldName();
            try
            {
                if (attribute.getGetMethod() == null) {
                    Class<?> type = info.getClass().getDeclaredField(fieldName).getType();
                    attribute.setGetMethod(getMethod(info.getClass(), 
                        generateMethodName((type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class) ? is_method_prefix : get_method_prefix), fieldName)));
                    attribute.setDateFormat(attribute.getGetMethod().getAnnotation(DateFormat.class));
                }
                valueTmp = attribute.getGetMethod().invoke(info);
            } catch (Exception e) {
                LOG.error("构建实体：{}的查询语句时发生错误", info.getClass(), e);
                throw new BuilderHqlException("查询构建Hql失败");
            }
            
            //值为空的情况下不添加查询
            if (valueTmp instanceof String && StringUtils.isBlank((String) valueTmp)) continue;
            else if (valueTmp == null) continue;
            
            //默认值不查询，当属性类型为int,long,shrot,float,double,boolean其中一种时，属性值一定不为空，那么需要当前的判断，默认值的情况下添加到查询里面
            if (attribute.getParams().defaultNotQuery() && new BigDecimal(valueTmp + "").compareTo(new BigDecimal(attribute.getParams().defaultNotQueryValue())) == 0) continue;
              
            //日期格式转换
            if (valueTmp instanceof String && attribute.getDateFormat() != null)
                valueTmp = DateUtil.parseDate((String) valueTmp, attribute.getDateFormat().value().format);
            //已经生成过一次查询代码的直接拼接
            if (StringUtils.isNotBlank(attribute.getHqlSection())) 
            {
                hql.append(attribute.getHqlSection());
                params.put(fieldName, (Serializable) valueTmp);
                continue;
            }
              
            int start = hql.length();
            hql.append(emtyp_interval_string).append(rightBrecket.equals(attribute.getParams().bracket()) ? rightBrecket : empty_string).append(attribute.getParams().value()).append(emtyp_interval_string)
                .append(leftBrecket.equals(attribute.getParams().bracket()) ? leftBrecket : empty_string).append("po.").append(attribute.getFieldName())
                .append(attribute.getParams().nexus().sign).append(":").append(fieldName);
            attribute.setHqlSection(hql.substring(start));
            params.put(fieldName, (Serializable) valueTmp);
        }
        
        if (StringUtils.isBlank(orderHQL)) {
            sortOrders();
            StringBuilder orderHQLTmp = new StringBuilder();
            for (int i = 0; i < orders.size(); i++)
                orderHQLTmp.append((i) == 0 ? " order by po." : ",po.").append(orders.get(i).getFieldName()).append(emtyp_interval_string).append(orders.get(i).getOrder());
            orderHQL = orderHQLTmp.toString();
        }
        return hql.append(orderHQL);
    }
    
    private void sortOrders()
    {
        Comparator<HqlOrderAttribute> comparator = new Comparator<HqlOrderAttribute>()
        {
            @Override
            public int compare(HqlOrderAttribute o1, HqlOrderAttribute o2)
            {
                if (new BigDecimal(o1.getIndex()).compareTo(new BigDecimal(o2.getIndex())) > 0)
                {  
                    return 1;  
                }
                else if (new BigDecimal(o1.getIndex()).compareTo(new BigDecimal(o2.getIndex()))< 0)
                {  
                    return -1;  
                }
                return 0;
            }
        };
        //开始排序
        Collections.sort(orders, comparator);
    }
    
    private static Method getMethod(Class<?> clazz, String methodName, Class<?>... types)
    {
        try
        {
            return clazz.getMethod(methodName, types);
        }
        catch (NoSuchMethodException | SecurityException e)
        {
            LOG.info("从目标:{}获取方法:{}失败", clazz.getName(), methodName);
            throw new RuntimeException("获取方法失败");
        }
    }
    
    private static String generateMethodName(String prefix, String fieldName)
    {
        if (fieldName.charAt(1) <= 90)
            return prefix + fieldName;
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
