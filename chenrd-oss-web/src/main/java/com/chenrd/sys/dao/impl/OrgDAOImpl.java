/*
 * 文件名：OrgDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月4日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import org.springframework.stereotype.Repository;

import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.OrganizationDAO;
import com.chenrd.sys.entity.Organization;

/**
 * 
 * @author chenrd
 * @version 2015年7月4日
 * @see OrgDAOImpl
 * @since
 */
@Repository("orgDAO")
public class OrgDAOImpl extends AbstractBaseDAO<Organization> implements OrganizationDAO {

}
