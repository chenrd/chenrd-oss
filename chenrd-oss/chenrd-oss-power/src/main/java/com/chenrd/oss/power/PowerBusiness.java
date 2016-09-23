/*
 * 文件名：PowerBusiness.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月6日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power;

import java.util.List;

import com.chenrd.business.Business;
import com.chenrd.common.Paging;
import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Domain;
import com.chenrd.example.VO;

public interface PowerBusiness extends Business
{
    
    <E extends Domain, T extends VO> List<T> find(String methodName, Class<E> entityClass, Class<T> clazz, QueryInfo info, Paging paging);
    
    <E extends Domain, T extends VO> List<T> find(String methodName, Class<E> entityClass, Class<T> clazz, QueryInfo info);
 
    
    <E extends Domain, T extends VO> List<T> find(Class<E> entityClass, Class<T> clazz, QueryInfo info, Paging paging);
    
    <E extends Domain, T extends VO> List<T> find(Class<E> entityClass, Class<T> clazz, QueryInfo info);
}
