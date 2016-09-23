/*
 * 文件名：Test.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.test;


import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test/resources/app-config-test.xml", "classpath:spring/ehcache.xml"})
public class Test
{
    @Autowired
    private SessionFactory sessionFactory;
    
    @org.junit.Test
    public void test() throws Exception
    {
        Map<String, ClassMetadata> metadatas = sessionFactory.getAllClassMetadata();
        for (Entry<String, ClassMetadata> metadata : metadatas.entrySet())
        {
            System.out.println(metadata.getKey());
        }
        System.out.println(1);
    }
}
