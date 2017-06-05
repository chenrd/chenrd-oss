/*
 * 文件名：RoleDAO.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.sys.entity.Role;

/**
 * 
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleDAO
 * @since
 */
public interface RoleDAO extends BaseDAO {

    /**
     * 
     * 查询列表数据
     * @param name 名称
     * @param paging 分页条件
     * @return JQueryTableResult
     * @see
     */
    List<Role> findPaging(String name, Paging paging);
    
    /**
     * 
     * 
     * @param key
     * @param createUser
     * @return 
     * @see
     */
    Role getByKey(String key);
}
