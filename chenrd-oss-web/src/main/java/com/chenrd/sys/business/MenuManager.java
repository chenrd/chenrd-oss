/*
 * 文件名：MenuManager.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business;

import java.util.List;

import com.chenrd.common.Paging;
import com.chenrd.oss.power.PowerBusiness;
import com.chenrd.sys.vo.MenuVO;

/**
 * 
 * 
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuManager
 * @since
 */
public interface MenuManager extends PowerBusiness {

	/**
	 * 
	 * 根据应用查询菜单集合
	 * 
	 * @param applyId
	 *            应用ID
	 * @param parentKey
	 *            菜单KEY
	 * @param paging
	 *            分页条件
	 * @return List<MenuVO>
	 * @see
	 */
	List<MenuVO> findChilds(Long applyId, String parentKey, Paging paging);

	/**
	 * 
	 * 
	 * @return
	 * @see
	 */
	List<MenuVO> findByUsername(String username);

	/**
	 * 
	 * 查询全部 排序key
	 * 
	 * @return List<MenuVO>
	 * @see
	 */
	List<MenuVO> findAll();

	/**
	 * 
	 * @param vo
	 */
	void saveOrUpdate(MenuVO vo);

	/**
	 * 
	 * 获取上一级目录。不存在则为空
	 * 
	 * @param key
	 * @return MenuVO
	 * @see
	 */
	MenuVO getParent(String key);

	/**
	 * 
	 * 获取菜单
	 * 
	 * @param key
	 * @return MenuVO
	 * @see
	 */
	MenuVO getByKey(String key);

	/**
	 * 
	 * 获取菜单
	 * 
	 * @param id
	 *            ""
	 * @return MenuVO
	 * @see
	 */
	MenuVO get(Long id);

	/**
	 * 
	 * 发布
	 * 
	 * @param id
	 *            ""
	 * @see
	 */
	void publish(Long id);

	/**
	 * 
	 * 删除
	 * 
	 * @param id
	 *            ""
	 * @see
	 */
	void deleted(Long id);
}
