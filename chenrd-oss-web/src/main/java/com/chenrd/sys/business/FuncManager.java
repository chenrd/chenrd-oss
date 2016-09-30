/*
 * 文件名：FuncManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.AttributeVO;

/**
 * 
 * @author chenrd
 * @version 2015年6月16日
 * @see FuncManager
 * @since
 */
public interface FuncManager extends PowerBusiness
{
    
    /**
     * 
     * 查询数据
     * @param applyId 应用ID
     * @param parentKey 父节点KEY
     * @param paging 分页条件
     * @return List<PowerInfo>
     * @see
     */
    List<PowerInfo> findChilds(Long applyId, String parentKey, Paging paging);
    
    /**
     * 获取菜单下面的所有功能
     * 
     * @param parentKey
     * @return 
     * @see
     */
    List<PowerInfo> findByParentKey(String parentKey);
    
    /**
     * 查询
     * 
     * @param username
     * @return 
     * @see
     */
    List<PowerInfo> findByUsername(String username);
    
    
    /**
     * 获取指定用户所有的字段权限 用于用户字段权限分配
     * 
     * @return 
     * @see
     */
    List<AttributeVO> findUserAllAttrPowers(String username);
    
    /**
     * 
     * 
     * @param key 标识
     * @return ""
     * @see
     */
    PowerInfo getByKey(String key);
    
    /**
     * 
     * @param id ""
     * @return ""
     * @see
     */
    PowerInfo get(Long id);
    
    /**
     * 
     * 保存更新
     * @param powerInfo ""
     * @see
     */
    void saveOrUpdate(PowerInfo powerInfo);
    
    /**
     * 
     * 发布
     * @param id ""
     * @see
     */
    void publish(Long id);
    
    /**
     * 
     * 删除
     * @param id ""
     * @see
     */
    void deleted(Long id);
}
