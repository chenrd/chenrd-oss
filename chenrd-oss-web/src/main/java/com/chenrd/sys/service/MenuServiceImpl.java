/*
 * 文件名：MenuServiceImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.sys.dao.MenuDAO;

/**
 * 
 * @author chenrd
 * @version 2015年6月12日
 * @see MenuServiceImpl
 * @since
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService
{
    /**
     * 
     */
    @Resource(name = "menuDAO")
    private MenuDAO menuDAO;

    @Transactional
    @Override
    public String queryFullByUrl(String url)
    {
        List<String> list = menuDAO.queryFullNameByUrl(url);
        String fullName = "";
        for (String name : list) 
        {
            fullName += name + " | ";
        }
        return fullName.length() == 0 ? "" : fullName.substring(0, fullName.length() - 3);
    }

    /**
     * @param menuDAO The menuDAO to set.
     */
    public void setMenuDAO(MenuDAO menuDAO)
    {
        this.menuDAO = menuDAO;
    }
    
}
