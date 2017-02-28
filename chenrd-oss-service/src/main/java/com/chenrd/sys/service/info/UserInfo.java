/*
 * 文件名：UserInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.chenrd.example.UserSessionParameter;
import com.chenrd.example.VO;
import com.chenrd.sys.service.PowerType;
import com.chenrd.sys.service.power.PowerLibrary;
/**
 * 
 * 用户信息保存类，储存在缓存中
 * @author chenrd
 * @version 2015年5月14日
 * @see UserInfo
 * @since
 */
public class UserInfo extends VO implements UserSessionParameter
{
    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -7741870372700677096L;
    
    /**
     * 
     */
    private String id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 曾用名
     */
    private String historyName;
    
    /**
     * 系统登录名
     */
    private String username;
    
    /**
     * 性别
     */
    private int sex;
    
    /**
     * 
     */
    private int type = 2;
    
    /**
     * 手机
     */
    private String phone;
    
    /**
     * 电话
     */
    private String telephone;
    
    /**
     * 邮箱
     */
    private String email;
    
    /**
     * 
     */
    private PowerLibrary library;
    
    /**
     * 
     */
    private Set<RoleInfo> roleSet;
    
    /**
     * 
     */
    private List<MenuInfo> menusSet;
    
    /**
     * 
     */
    private Map<String, PowerInfo> powers;
    
    private Map<String, List<String>> attributes;
    
    /**
     * 
     */
    private Set<ApplyInfo> applys;
    
    /**
     * 来源系统的key
     */
    private String source;
    
    
    public boolean attributesContainsKey(String key)
    {
        return attributes == null ? false : attributes.containsKey(key);
    }
    
    
    
    
    /**
	 * @param id
	 * @param name
	 * @param username
	 */
	public UserInfo(String id, String name, String username) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
	}




	/**
     * 
     * @param name
     * @param username
     */
    public UserInfo(String name, String username)
    {
        super();
        this.name = name;
        this.username = username;
    }

    /**
     * 
     */
    public UserInfo()
    {
        super();
    }

    /**
     * @return Returns the id.
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id The id to set.
     */
    public void setId(String id)
    {
        this.id = id;
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
     * @return Returns the historyName.
     */
    public String getHistoryName()
    {
        return historyName;
    }

    /**
     * @param historyName The historyName to set.
     */
    public void setHistoryName(String historyName)
    {
        this.historyName = historyName;
    }

    /**
     * @return Returns the username.
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username The username to set.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return Returns the sex.
     */
    public int getSex()
    {
        return sex;
    }

    /**
     * @param sex The sex to set.
     */
    public void setSex(int sex)
    {
        this.sex = sex;
    }

    /**
     * @return Returns the phone.
     */
    public String getPhone()
    {
        return phone;
    }

    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * @return Returns the telephone.
     */
    public String getTelephone()
    {
        return telephone;
    }

    /**
     * @param telephone The telephone to set.
     */
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }

    /**
     * @return Returns the email.
     */
    public String getEmail()
    {
        return email;
    }

    /**
     * @param email The email to set.
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the library.
     */
    public PowerLibrary getLibrary()
    {
        return library;
    }

    /**
     * @param library The library to set.
     */
    public void setLibrary(PowerLibrary library)
    {
        this.library = library;
    }

    /**
     * @return Returns the roleSet.
     */
    public Set<RoleInfo> getRoleSet()
    {
        return roleSet;
    }

    /**
     * @param roleSet The roleSet to set.
     */
    public void setRoleSet(Set<RoleInfo> roleSet)
    {
        this.roleSet = roleSet;
    }

    /**
     * @return Returns the menusSet.
     */
    public List<MenuInfo> getMenusSet()
    {
        return menusSet;
    }

    /**
     * @param menusSet The menusSet to set.
     */
    public void setMenusSet(List<MenuInfo> menusSet)
    {
        this.menusSet = menusSet;
    }

    /**
     * @return Returns the applys.
     */
    public Set<ApplyInfo> getApplys()
    {
        return applys;
    }

    /**
     * @param applys The applys to set.
     */
    public void setApplys(Set<ApplyInfo> applys)
    {
        this.applys = applys;
    }

    /**
     * @return Returns the powers.
     */
    public Map<String, PowerInfo> getPowers()
    {
        return powers;
    }

    /**
     * @param powers The powers to set.
     */
    public void setPowers(Map<String, PowerInfo> powers)
    {
        this.powers = powers;
    }

    /**
     * @return Returns the type.
     */
    public int getType()
    {
        return type;
    }

    /**
     * @param type The type to set.
     */
    public void setType(int type)
    {
        this.type = type;
    }
    
    public List<PowerInfo> listFuncsList()
    {
        List<PowerInfo> infos = new ArrayList<PowerInfo>();
        for (String key : this.powers.keySet())
        {
            if (this.powers.get(key).getType() == PowerType.FUNC_POWER)
            {
                infos.add(this.powers.get(key));
            }
        }
        sort(infos);
        return infos;
    }
    
    private void sort(List<PowerInfo> ls)
    {
        final Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
        //排序定义
        Comparator<PowerInfo> comparator = new Comparator<PowerInfo>()
        {
            @Override
            public int compare(PowerInfo o1, PowerInfo o2)
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
        Collections.sort(ls, comparator);
    }
    
    /**
     * @return Returns the attributes.
     */
    public Map<String, List<String>> getAttributes()
    {
        return attributes;
    }

    /**
     * @param attributes The attributes to set.
     */
    public void setAttributes(Map<String, List<String>> attributes)
    {
        this.attributes = attributes;
    }

    /**
     * @return Returns the source.
     */
    public String getSource()
    {
        return source;
    }

    /**
     * @param source The source to set.
     */
    public void setSource(String source)
    {
        this.source = source;
    }
    
}
