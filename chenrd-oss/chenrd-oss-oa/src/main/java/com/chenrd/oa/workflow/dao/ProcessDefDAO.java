/*
 * 文件名：ProcessDefDAO.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2017年2月10日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oa.workflow.dao;

import org.springframework.stereotype.Repository;

import com.chenrd.dao.BaseDAO;
import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.oa.workflow.entity.ProcessDef;

@Repository("processDefDAO")
public class ProcessDefDAO extends AbstractBaseDAO<ProcessDef> implements BaseDAO {

}
