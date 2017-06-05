/*
 * 文件名：Menu.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2015年5月12日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.chenrd.sys.service.PowerType;

/**
 * 
 * 菜单
 * @author chenrd
 * @version 2015年5月12日
 * @see Menu
 * @since
 */
@Entity
@DiscriminatorValue("0")
public class Menu extends Power {

    /**
     * <br>
     */
    private static final long serialVersionUID = 3007766633873549998L;
    
    
    /**
     * 样式
     */
    @Column(name = "ICON_", length = 50)
    private String icon;
    
    

    /**
     * 
     */
    public Menu() {
        super();
        super.setType(PowerType.MENU_POWER);
    }

    /**
     * @return Returns the icon.
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon The icon to set.
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
}
