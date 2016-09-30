/*
 * 文件名：MenuManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月19日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.DateUtil;
import com.chenrd.common.Paging;
import com.chenrd.dao.BaseDAO;
import com.chenrd.dao.BeanUtil;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.sys.business.FuncManager;
import com.chenrd.sys.business.MenuManager;
import com.chenrd.sys.dao.ApplyDAO;
import com.chenrd.sys.dao.MenuDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.service.PowerEnum;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.vo.MenuVO;

/**
 * 
 * 菜单业务处理类
 * @author chenrd
 * @version 2015年5月19日
 * @see MenuManagerImpl
 * @since
 */
@Transactional
@Service("menuManager")
public class MenuManagerImpl extends AbstractPowerBusiness implements MenuManager
{
    /**
     * 
     */
    @Resource(name = "menuDAO")
    private MenuDAO menuDAO;
    
    /**
     * 
     */
    @Resource(name = "applyDAO")
    private ApplyDAO applyDAO;
    
    @Resource(name = "funcManager")
    private FuncManager funcManager;
    
    /*
     * (non-Javadoc)
     * @see com.chenrd.sys.business.MenuManager#findChilds(java.lang.Long, java.lang.String)
     */
    @Override
    public List<MenuVO> findChilds(Long applyId, String parentKey, Paging paging)
    {
        return returnList(menuDAO.findChilds(applyId, parentKey, paging));
    }
    
    /**
     * 
     * @param list List<Menu>
     * @return List<MenuVO>
     * @see
     */
    private List<MenuVO> returnList(List<Menu> list) 
    {
        List<MenuVO> vos = new ArrayList<MenuVO>();
        MenuVO vo = null;
        for (Menu menu : list) 
        {
            vo = new MenuVO();
            BeanUtils.copyProperties(menu, vo);
            vo.setApplyId(menu.getApply().getId());
            vo.setApplyName(menu.getApply().getName());
            vo.setCreateDate(DateUtil.formatDate(menu.getCreateDate()));
            vos.add(vo);
        }
        return vos;
    }
    
    /*
     * (non-Javadoc)
     * @see com.chenrd.sys.business.MenuManager#saveOrUpdate(com.chenrd.sys.vo.MenuVO)
     */
    @Override
    @Transactional
    public void saveOrUpdate(MenuVO vo)
    {
        Apply apply = applyDAO.get(Apply.class, vo.getApplyId());
        if (apply == null) 
        {
            throw new RuntimeException("添加菜单失败，没有找到id=[" + vo.getApplyId() +"]的应用");
        }
        Menu menu = null;
        if (vo.getId() != null) 
        {
            menu = menuDAO.get(Menu.class, vo.getId());
            vo.setStatus(menu.getStatus());
        }
        else 
        {
            menu = new Menu();
            menu.setId(null);
        }
        BeanUtil.copyProperties(vo, menu);
        menu.setApply(apply);
        menu.setKey((StringUtils.isNotBlank(vo.getParentKey()) ? vo.getParentKey() + "/" : apply.getKey() + "/" + PowerEnum.MENU.getKey() + "/") + vo.getKey());
        //全称
        if (StringUtils.isNotBlank(vo.getParentKey())) 
        {
            Menu parent = menuDAO.getByProperties(Menu.class, "key", menu.getParentKey());
            menu.setFullName(parent.getFullName() + "-" + menu.getName());
        }
        else
        {
            menu.setFullName(apply.getKey() + "-" + menu.getName());
        }
        //应用下面的第一层目录
        if (!StringUtils.isNotBlank(menu.getParentKey()))
        {
            menu.setParentKey(apply.getKey());
        }
        menuDAO.saveOrUpdate(menu);
        if (vo.getFuncNames() != null)
        {
            for (int i = 0; i < vo.getFuncNames().length; i++)
            {
                if (!StringUtils.isNotBlank(vo.getFuncNames()[i]))
                {
                    continue;
                }
                funcManager.saveOrUpdate(new PowerInfo(vo.getFuncIds() == null || (vo.getFuncIds().length - 1) < i ? null : vo.getFuncIds()[i], vo.getFuncNames()[i], (i + 1) * 100 + "", vo.getFuncUrls()[i], menu.getKey(), apply.getId(), apply.getKey()));
            }
        }
    }

    /*
     * (non-Javadoc)
     * @see com.chenrd.sys.business.MenuManager#getParent(java.lang.String)
     */
    @Override
    public MenuVO getParent(String key)
    {
        if (key.lastIndexOf("/") == -1) 
        {
            return null;
        }
        
        Menu menu = menuDAO.getByKey(key.substring(0, key.lastIndexOf("/")));
        if (menu == null) 
        {
            return null;
        }
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(menu, vo);
        vo.setApplyId(menu.getApply().getId());
        vo.setApplyName(menu.getApply().getName());
        return vo;
    }
    
    @Override
    public MenuVO getByKey(String key)
    {
        Menu menu = menuDAO.getByKey(key);
        if (menu == null) 
        {
            return null;
        }
        MenuVO vo = new MenuVO();
        BeanUtils.copyProperties(menu, vo);
        vo.setApplyId(menu.getApply().getId());
        vo.setApplyName(menu.getApply().getName());
        return vo;
    }

    /**
     * @param menuDAO The menuDAO to set.
     */
    public void setMenuDAO(MenuDAO menuDAO)
    {
        this.menuDAO = menuDAO;
    }

    @Override
    public List<MenuVO> findAll()
    {
        return menuDAO.findAll();
    }

    @Override
    public void publish(Long id)
    {
        Menu menu = menuDAO.get(Menu.class, id);
        menu.setStatus(menu.getStatus() == 0 ? 1 : 0);
        menuDAO.update(menu);
    }

    @Override
    public void deleted(Long id)
    {
        Menu menu = menuDAO.get(Menu.class, id);
        menu.setStatus(-1);
        menuDAO.update(menu);
    }

    @Override
    public MenuVO get(Long id)
    {
        Menu menu = menuDAO.get(Menu.class, id);
        if (menu == null)
        {
            return null;
        }
        MenuVO vo = new MenuVO();
        BeanUtil.copyProperties(menu, vo);
        vo.setFullKey(vo.getKey());
        vo.setKey(menu.getApply().getKey().equals(menu.getParentKey()) ? menu.getKey().replace(menu.getApply().getKey() + "/" + PowerEnum.MENU.getKey() + "/", "") : menu.getKey().replace(menu.getParentKey() + "/", ""));
        return vo;
    }

    @Override
    public List<MenuVO> findByUsername(String username)
    {
      //获取与角色相关的菜单权限
        List<Menu> menus = menuDAO.findRolePowerByUsername(username);
        //获取直接关联用户的权限
        List<Menu> ms = menuDAO.findUserPowerByUsername(username);
        
        if (menus == null)
        {
            menus = new ArrayList<Menu>();
        }
        if (ms != null)
        {
            for (Menu menu : ms)
            {
                if (!menus.contains(menu))
                {
                    menus.add(menu);
                }
            }
        }
        
        final Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        //排序定义
        Comparator<Power> comparator = new Comparator<Power>()
        {
            @Override
            public int compare(Power o1, Power o2)
            {
                if (cmp.compare(o1.getKey(), o2.getKey()) > 0)
                {  
                    return 1;  
                }
                else if (cmp.compare(o1.getKey(), o2.getKey()) < 0)
                {  
                    return -1;  
                }
                return 0;
            }
        };
      //开始排序
        Collections.sort(menus, comparator);
        return BeanUtil.returnList(menus, MenuVO.class);
    }

    @Override
    public BaseDAO getDAO()
    {
        return menuDAO;
    }

}
