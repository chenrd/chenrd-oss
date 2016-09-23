/*
 * 文件名：RoleManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月25日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.sys.service.info.RoleInfo;

/**
 * 
 * @author chenrd
 * @version 2015年5月25日
 * @see RoleManager
 * @since
 */
public interface RoleManager
{

    /**
     * 
     * 查询列表数据
     * @param name 名称
     * @param paging 分页条件
     * @return JQueryTableResult
     * @see
     */
    List<RoleInfo> findPaging(String name, Paging paging);
    
    /**
     * 查询全部
     * @return 全部
     */
    List<RoleInfo> findALL();
    
    /**
     * 
     * 
     * @param id 
     * @return RoleInfo
     * @see
     */
    RoleInfo get(Long id);
    
    /**
     * 
     * 添加角色
     * @param info 
     * @param powers 
     * @return 
     * @see
     */
    void saveOrUpdate(RoleInfo info, Long[] powers);
    
    /**
     * 
     * 
     * @param id 
     * @see
     */
    void publish(Long id);
    
    /**
     * 
     * 
     * @param id  
     * @see
     */
    void deleted(Long id);
}
