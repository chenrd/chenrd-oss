/*
 * 文件名：MenuInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import java.util.ArrayList;
import java.util.List;

import com.chenrd.example.VO;

/**
 * 
 * @author chenrd
 * @version 2015年6月15日
 * @see MenuInfo
 * @since
 */
public class MenuInfo extends VO
{
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 4559110705965323305L;

    /**
     * 
     */
    private Long id;
    
    /**
     * 类型
     */
    private String type;
    
    /**
     * 名称
     */
    private String name;
    
    /**
     * 001/002/003
     */
    private String key;
    
    /**
     * 
     */
    private String value;
    
    /**
     * 创建日期
     */
    private String createDate;
    
    /**
     * 
     */
    private Long applyId;
    
    /**
     * 
     */
    private String applyName;
    
    /**
     * 是否发布
     */
    private boolean publish = false;
    
    /**
     * 菜单访问路径
     */
    private String url;
    
    /**
     * 样式
     */
    private String icon;
    
    /**
     * 
     */
    private String parentKey;
    
    /**
     * 
     */
    private String parentName;
    
    /**
     * 
     */
    private String fullName;
    
    /**
     * 
     */
    private List<MenuInfo> childs;
    
    /**
     * 
     */
    private MenuInfo parent;

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(Long id)
    {
        this.id = id;
    }

    /**
     * @return Returns the type.
     */
    public String getType()
    {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(String type)
    {
        this.type = type;
    }

    /**
     * @return Returns the name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return Returns the key.
     */
    public String getKey()
    {
        return key;
    }

    /**
     * @param key The key to set.
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * @return Returns the value.
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @param value The value to set.
     */
    public void setValue(String value)
    {
        this.value = value;
    }

    /**
     * @return Returns the createDate.
     */
    public String getCreateDate()
    {
        return createDate;
    }

    /**
     * @param createDate The createDate to set.
     */
    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }

    /**
     * @return Returns the applyId.
     */
    public Long getApplyId()
    {
        return applyId;
    }

    /**
     * @param applyId The applyId to set.
     */
    public void setApplyId(Long applyId)
    {
        this.applyId = applyId;
    }

    /**
     * @return Returns the applyName.
     */
    public String getApplyName()
    {
        return applyName;
    }

    /**
     * @param applyName The applyName to set.
     */
    public void setApplyName(String applyName)
    {
        this.applyName = applyName;
    }

    /**
     * @return Returns the publish.
     */
    public boolean isPublish()
    {
        return publish;
    }

    /**
     * @param publish The publish to set.
     */
    public void setPublish(boolean publish)
    {
        this.publish = publish;
    }

    /**
     * @return Returns the url.
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url The url to set.
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return Returns the icon.
     */
    public String getIcon()
    {
        return icon;
    }

    /**
     * @param icon The icon to set.
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    /**
     * @return Returns the parentKey.
     */
    public String getParentKey()
    {
        return parentKey;
    }

    /**
     * @param parentKey The parentKey to set.
     */
    public void setParentKey(String parentKey)
    {
        this.parentKey = parentKey;
    }

    /**
     * @return Returns the parentName.
     */
    public String getParentName()
    {
        return parentName;
    }

    /**
     * @param parentName The parentName to set.
     */
    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    /**
     * @return Returns the fullName.
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * @param fullName The fullName to set.
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the childs.
     */
    public List<MenuInfo> getChilds()
    {
        return childs == null ? childs = new ArrayList<MenuInfo>() : childs;
    }

    /**
     * @param childs The childs to set.
     */
    public void setChilds(List<MenuInfo> childs)
    {
        this.childs = childs;
    }

    /**
     * @return Returns the parent.
     */
    public MenuInfo getParent()
    {
        return parent;
    }

    /**
     * @param parent The parent to set.
     */
    public void setParent(MenuInfo parent)
    {
        this.parent = parent;
    }
    
    
    
}
