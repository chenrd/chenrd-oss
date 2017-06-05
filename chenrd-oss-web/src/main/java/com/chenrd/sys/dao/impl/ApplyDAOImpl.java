/*
 * 文件名：ApplyDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.ApplyDAO;
import com.chenrd.sys.entity.Apply;

/**
 * 
 * @author chenrd
 * @version 2015年5月15日
 * @see ApplyDAOImpl
 * @since
 */
@Repository("applyDAO")
public class ApplyDAOImpl extends AbstractBaseDAO<Apply> implements ApplyDAO {

}
