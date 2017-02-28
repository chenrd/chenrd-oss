/*
 * 文件名：UserServiceImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.Encryption;
import com.chenrd.dao.BeanUtil;
import com.chenrd.shiro.PropertiesBean;
import com.chenrd.sys.business.impl.UserManagerImpl;
import com.chenrd.sys.dao.MenuDAO;
import com.chenrd.sys.dao.PowerDAO;
import com.chenrd.sys.dao.RoleDAO;
import com.chenrd.sys.dao.UserDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Menu;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.entity.Role;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.service.info.ApplyInfo;
import com.chenrd.sys.service.info.BaseUserInfo;
import com.chenrd.sys.service.info.LogInfo;
import com.chenrd.sys.service.info.MenuInfo;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.service.info.UserInfo;

/**
 * 
 * @author chenrd
 * @version 2015年6月15日
 * @see UserServiceImpl
 * @since
 */
@Service("userService")
public class UserServiceImpl extends UserManagerImpl implements UserService
{
    
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    
    @Resource(name = "menuDAO")
    private MenuDAO menuDAO;
    
    @Resource(name = "powerDAO")
    private PowerDAO powerDAO;
    
    @Resource(name = "roleDAO")
    private RoleDAO roleDAO;
    
    @Resource(name = "propertiesBean")
    private PropertiesBean propertiesBean;
    
    @Resource(name = "logRecordService")
    private LogRecordService logRecordService;
    
    @Transactional
    @Override
    public UserInfo findPowerByUsername(String username, String applyKey)
    {
        User user = userDAO.getByProperties(User.class, new String[]{"username", "status"}, new Object[]{username, Status.NO});
        if (user == null)
        {
            return null;
        }
        UserInfo info = new UserInfo();
        BeanUtil.copyProperties(user, info);
        //获取与角色相关的菜单权限
        List<Menu> menus = menuDAO.findRolePowerByUsername(username, applyKey);
        //获取直接关联用户的权限
        List<Menu> ms = menuDAO.findUserPowerByUsername(username, applyKey);
        
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
       
        sort(menus);
        info.setMenusSet(BeanUtil.returnList(menus, MenuInfo.class));
        
        //添加功能权限及属性权限
        List<Power> powers = powerDAO.findUserFuncPower(username, applyKey);
        List<Power> ps = powerDAO.findRoleFuncPower(username, applyKey);
        if (powers == null)
        {
            powers = new ArrayList<Power>();
        }
        if (ps != null)
        {
            powers.addAll(ps);
        }
        Map<String, PowerInfo> map = new HashMap<String, PowerInfo>();
        List<PowerInfo> list = BeanUtil.returnList(powers, PowerInfo.class);
        for (PowerInfo powerInfo : list)
        {
            map.put(powerInfo.getUrl(), powerInfo);
        }
        info.setPowers(map);
        
        //用户字段权限获取
        List<Power> fields = powerDAO.findUserFieldPower(username, applyKey);
        if (fields != null) 
        {
            Map<String, List<String>> attrs = new HashMap<String, List<String>>();
            for (Power power : fields) {
                if (attrs.get(power.getParentKey()) == null)
                    attrs.put(power.getParentKey(), new ArrayList<String>());
                
                attrs.get(power.getParentKey()).add(((Attribute) power).getValue());
            }
            info.setAttributes(attrs);
        }
        
        //用户快捷链接获取
        if (user.getApplys() != null)
        {
            Set<ApplyInfo> infos = new HashSet<ApplyInfo>();
            for (Apply apply : user.getApplys())
            {
                ApplyInfo applyInfo = new ApplyInfo();
                BeanUtil.copyProperties(apply, applyInfo);
                infos.add(applyInfo);
            }
            info.setApplys(infos);
        }
        return info;
    }
    
    /**
     * 
     * @param powers Power
     * @see
     */
    private void sort(List<? extends Power> powers)
    {
        //指定中文排序
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
        Collections.sort(powers, comparator);
    }
    
    @Transactional
    @Override
    public List<UserInfo> findSelect()
    {
    	List<UserInfo> infos = new ArrayList<UserInfo>();
    	List<User> list = userDAO.findByProperty(User.class, "status", Status.NO, "id");
    	UserInfo info = null;
    	for (User u : list) {
    		info = new UserInfo();
    		info.setId(u.getId());
    		info.setUsername(u.getUsername());
    		info.setName(u.getName());
    		infos.add(info);
    	}
        return infos;
    }
    
    /**
     * @param userDAO The userDAO to set.
     */
    public void setUserDAO(UserDAO userDAO)
    {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public String saveOrUpdate(BaseUserInfo userInfo, String applyKey)
    {
        User user = null;
        if (StringUtils.isNotBlank(userInfo.getId())) 
        {
            user = (User) userDAO.get(User.class, userInfo.getId());
            userInfo.setUsername(user.getUsername());
        } 
        else 
        {
            if ((user = userDAO.getByProperties(User.class, "username", userInfo.getUsername())) != null)
            {
                return user.getId();
            }
            user = new User();
            userInfo.setId(null);
            user.setCreateDate(new Date());
            user.setPassword(Encryption.encodeByMD5(propertiesBean.getDefaultPassword()).toLowerCase());
        }
        user.setStatus(Status.NO);
        user.setUpdateDate(new Date());
        BeanUtil.copyProperties(userInfo, user);
        user.setApplyKey(applyKey);
        userDAO.saveOrUpdate(user);
        
        if (StringUtils.isNotBlank(userInfo.getDefaultRole()))
        {
            Role role = roleDAO.getByKey(userInfo.getDefaultRole());
            if (role == null)
            {
                logRecordService.newLogRecord(new LogInfo(null, LogInfo.TYPE_ERROR, new Date(), userInfo.getCreateUser(), applyKey, "添加默认角色错误", "错误原因：（" + userInfo.getDefaultRole() + "）当前角色不存在，或者当前用户不具备分配权限"));
            } else
            {
            	Set<Role> roles = new HashSet<Role>();
                roles.add(role);
                user.setRoles(roles);
                userDAO.update(user);
            }
        }
        if (userInfo.getRoleIds() != null) {
        	Set<Role> roles = new HashSet<Role>();
        	for (Long roleId : userInfo.getRoleIds()) {
        		Role role = (Role) roleDAO.get(roleId);
        		roles.add(role);
        		user.setRoles(roles);
        		userDAO.update(user);
        	}
        }
        return user.getId();
    }

    @Transactional
    @Override
    public void resetPassword(String id, String username)
    {
        User user = userDAO.get(User.class, id);
        user.setPassword(Encryption.encodeByMD5(propertiesBean.getDefaultPassword()).toLowerCase());
        user.setUpdateDate(new Date());
        user.setUpdateUser(username);
        userDAO.update(user);
    }

    @Override
    public int modifyPassword(String username, String old, String newPassword)
    {
        User user = userDAO.getByProperties(User.class, "username", username);
        if (user == null)
        {
            return 1;
        }
        if (!user.getPassword().equalsIgnoreCase(Encryption.encodeByMD5(old)))
        {
            return 2;
        }
        user.setPassword(Encryption.encodeByMD5(newPassword).toLowerCase());
        userDAO.update(user);
        return 0;
    }

    @Override
    public void delete(String id)
    {
        
    }

    @Transactional
	@Override
	public void allotFields(String[] userIds, Long fieldId) {
		if (userIds != null) {
			for (String id : userIds) {
				super.allotField(id, fieldId);
			}
		}
	}

    @Transactional
	@Override
	public void deleteFields(String[] userIds, Long fieldId) {
		if (userIds != null) {
			for (String id : userIds) {
				super.deleteField(id, fieldId);
			}
		}
	}
    
    @Override
	public List<UserInfo> findFieldByPowerKey(String key) {
		return userDAO.findFieldByPowerKey(key);
	}
}
