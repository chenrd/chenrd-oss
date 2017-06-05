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
import java.util.Arrays;
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
import com.chenrd.dao.em.Nexus;
import com.chenrd.dao.info.HqlAttribute;
import com.chenrd.dao.info.HqlOrderAttribute;
import com.chenrd.dao.info.QueryInfo;

public class SimpleEntityQueryBuilder implements EntityQueryBuilder {
    
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 2511829826226040290L;

    private static final Logger LOG = LoggerFactory.getLogger(SimpleEntityQueryBuilder.class);
    
    private Class<?> clazz;
    
    List<HqlAttribute> list = new ArrayList<HqlAttribute>();
    
    private List<HqlOrderAttribute> orders = new ArrayList<HqlOrderAttribute>();
    
    private String orderHQL;
    
    public static EntityQueryBuilder newEntityQueryBuilder() {
        return new SimpleEntityQueryBuilder();
    }
    
    public EntityQueryBuilder with(Class<?> clazz) {
        this.clazz = clazz;
        return this;
    }
    
    @Override
    public EntityQueryBuilder with(HqlAttribute hqlAttribute) {
        list.add(hqlAttribute);
        return this;
    }

    @Override
    public EntityQueryBuilder with(String fieldName, QueryOrder order) {
        orders.add(new HqlOrderAttribute(fieldName, order.index(), order.value()));
        return this;
    }
    
    /**
     * 补充说明
     * 参数： params 如果是由PowerEntityQueryBuilder实例执行，那么限制了查询条件，
     */
    @Override
    public StringBuilder builder(QueryInfo info, final Map<String, Serializable> params) {
        StringBuilder hql = new StringBuilder("from ").append(clazz.getSimpleName()).append(" as po where 1=1");
        if (info != null) {
        	Object valueTmp = null;
        	for (HqlAttribute attribute : list) {
                String fieldName = StringUtils.isNotBlank(attribute.getParams().name()) ? attribute.getParams().name() : attribute.getFieldName();
                try {
                    if (attribute.getGetMethod() == null) {
                    	//代码是缓存getMethod的方法，下次就不需要在通过Class反射得到节省代码开销，同时需要注意，这里缓存之后，那么查询的info对象就必须始终是同一个class
                        Class<?> type = info.getClass().getDeclaredField(fieldName).getType();
                        attribute.setGetMethod(getMethod(info.getClass(), 
                            generateMethodName((type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class) ? "is" : "get"), fieldName)));
                        attribute.setDateFormat(attribute.getGetMethod().getAnnotation(DateFormat.class));
                    }
                    valueTmp = attribute.getGetMethod().invoke(info);
                    if (valueTmp != null && params.containsKey(fieldName)) {
                    	//这一段代码结合方法的补充说明一起看，使用oss的field限制查询，如果params里面有这个属性的限制条件，那么valueTmp的值不能超过这个范围，重点：目的就是为了防止漏洞检索，被懂的人直接检索的限制意外的信息
                    	if (!Arrays.asList((String[]) params.get(fieldName)).contains(valueTmp.toString())) {
                    		valueTmp = null;
                    	}
                    }
                    if (valueTmp == null) valueTmp = params.get(fieldName);
                } catch (Exception e) {
                    throw new BuilderHqlException("构建实体：{" + info.getClass() + "}的查询语句时发生错误", e);
                }
                
                //值为空的情况下不添加查询
                if (valueTmp instanceof String && StringUtils.isBlank((String) valueTmp)) continue;
                else if (valueTmp == null) continue;
                
                //查询连接关系为in的时候需要注意
                if (valueTmp instanceof String[] && ((String[]) valueTmp).length == 0) continue;
                
                //默认值不查询，当属性类型为int,long,shrot,float,double,boolean其中一种时，属性值一定不为空，那么需要当前的判断，默认值的情况下添加到查询里面
                if (attribute.getParams().defaultNotQuery() && new BigDecimal(valueTmp + "").compareTo(new BigDecimal(attribute.getParams().defaultNotQueryValue())) == 0) continue;
                  
                //日期格式转换
                if (valueTmp instanceof String && attribute.getDateFormat() != null)
                    valueTmp = DateUtil.parseDate((String) valueTmp, attribute.getDateFormat().value().format);
                //已经生成过一次查询代码的直接拼接
                if (StringUtils.isNotBlank(attribute.getHqlSection())) {
                    hql.append(attribute.getHqlSection());
                    params.put(fieldName, (Serializable) valueTmp);
                    continue;
                }
                  
                int start = hql.length();
                hql.append(" ").append(")".equals(attribute.getParams().bracket()) ? ")" : "")
                    .append(attribute.getParams().value()).append(" ") //attribute.getParams().value() is and or
                    .append("(".equals(attribute.getParams().bracket()) ? "(" : "");
                
                //如果是LimitField属性，特殊处理
                if (valueTmp instanceof Object[] && ((Object[]) valueTmp).length > 1) {
                    hql.append("po.").append(attribute.getFieldName()).append(" ").append(Nexus.IN.sign).append(" ").append("(");
                    int index = 1;
                    for (Object temp : (Object[]) valueTmp) {
                        hql.append(index == 1 ? "" : ",").append(":").append(fieldName).append(index);
                        params.put(fieldName + (index++), changeValueType(attribute.getGetMethod().getReturnType(), temp));
                    }
                    hql.append(")");
                    params.remove(fieldName); //因为params是最后作为查询的时候query.setProperties(Map) 是的参数，所以不可以保留多余的参数，这个位置需要删除掉
                } else {
                	if (attribute.getParams().nexus().type == 1) { //两种情况，自定义数据库函数的时候，hql的查询代码需要有些不同
                    	hql.append("po.").append(attribute.getFieldName()).append(" ").append(attribute.getParams().nexus().sign).append(" ").append(":").append(fieldName);
                    } else {
                    	//调用自定义sql方法
                    	hql.append(attribute.getParams().nexus().sign).append("(po.").append(attribute.getFieldName()).append(", :").append(fieldName).append(")>0");
                    }
                    if (valueTmp instanceof Object[]) {
                        params.put(fieldName, changeValueType(attribute.getGetMethod().getReturnType(), ((Object[]) valueTmp)[0]));
                    } else {
                        params.put(fieldName, (Serializable) valueTmp);
                        if (attribute.isCachehqlSection()) {//应许缓存的情况，保存起来
                        	attribute.setHqlSection(hql.substring(start));
                        }
                    }
                }
            }
        }
        
        if (StringUtils.isBlank(orderHQL)) {
            sortOrders();
            StringBuilder orderHQLTmp = new StringBuilder();
            for (int i = 0; i < orders.size(); i++)
                orderHQLTmp.append((i) == 0 ? " order by po." : ",po.").append(orders.get(i).getFieldName()).append(" ").append(orders.get(i).getOrder());
            orderHQL = orderHQLTmp.toString();
        }
        return hql.append(orderHQL);
    }
    
    
    /**
     * 修改value为需要的数据类型
     * 
     * @param clas 需要的类型
     * @param value 要改变的值
     * @return 
     * @see
     */
    private Serializable changeValueType(Class<?> clas, Object value) {
    	if (clas.isAssignableFrom(value.getClass())) {
    		return (Serializable) value;
    	}
    	String tempValue = null;
    	if (value instanceof String) {
    		tempValue = (String) value;
    	} else {
    		tempValue = value.toString();
    	}
    	if (Object[].class.isAssignableFrom(clas)) {
    		clas = clas.getComponentType();
    	}
        if (clas.isAssignableFrom(String.class)) {
        	return tempValue;
        }
        if (clas.isAssignableFrom(int.class) || clas.isAssignableFrom(Integer.class))
            return Integer.parseInt(tempValue);
        if (clas.isAssignableFrom(long.class) || clas.isAssignableFrom(Long.class))
            return Long.parseLong(tempValue);
        return tempValue;
    }
    
    public static void main(String[] args) {
    	
		System.out.println(Object[].class.getComponentType());
		System.out.println(Object[].class.getEnclosingClass());
	}
    
    private void sortOrders() {
        Comparator<HqlOrderAttribute> comparator = new Comparator<HqlOrderAttribute>() {
            @Override
            public int compare(HqlOrderAttribute o1, HqlOrderAttribute o2) {
                if (new BigDecimal(o1.getIndex()).compareTo(new BigDecimal(o2.getIndex())) > 0) {  
                    return 1;  
                } else if (new BigDecimal(o1.getIndex()).compareTo(new BigDecimal(o2.getIndex()))< 0) {  
                    return -1;  
                }
                return 0;
            }
        };
        //开始排序
        Collections.sort(orders, comparator);
    }
    
    private static Method getMethod(Class<?> clazz, String methodName, Class<?>... types) {
        try {
            return clazz.getMethod(methodName, types);
        } catch (NoSuchMethodException | SecurityException e) {
            LOG.info("从目标:{}获取方法:{}失败", clazz.getName(), methodName);
            throw new RuntimeException("获取方法失败");
        }
    }
    
    private static String generateMethodName(String prefix, String fieldName) {
        if (fieldName.charAt(1) <= 90)
            return prefix + fieldName;
        return prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}
