/*
 * 文件名：UserManagerImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.business.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chenrd.common.BeanCopyUtils;
import com.chenrd.common.Encryption;
import com.chenrd.dao.BaseDAO;
import com.chenrd.oss.power.abs.AbstractPowerBusiness;
import com.chenrd.oss.power.ann.DefClassPower;
import com.chenrd.sys.business.UserManager;
import com.chenrd.sys.dao.UserDAO;
import com.chenrd.sys.entity.Apply;
import com.chenrd.sys.entity.Attribute;
import com.chenrd.sys.entity.Power;
import com.chenrd.sys.entity.Role;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.entity.UserPowerMapping;
import com.chenrd.sys.entity.classKey.UserPowerKey;
import com.chenrd.sys.service.PowerService;
import com.chenrd.sys.vo.UserVO;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserManagerImpl
 * @since
 */
@Transactional
@Service("userManager")
public class UserManagerImpl extends AbstractPowerBusiness implements UserManager
{

    /**
     * 
     */
    @Resource(name = "userDAO")
    private UserDAO userDAO;
    
    /**
     * 
     */
    @Value("#{settings['default.password']}")
    private String defaultPassword;
    
    @Resource(name = "powerService")
    private PowerService powerService;
    
    @Value("#{settings['apply.key']}")
    private String applyKey;
    
    @Override
    public String saveOrUpdate(UserVO userInfo)
    {
        boolean currentNoCopy = false;
        User user = null;
        if (StringUtils.isNotBlank(userInfo.getId())) 
        {
            user = (User) userDAO.get(User.class, userInfo.getId());
            currentNoCopy = true;
        } 
        else 
        {
            if (userDAO.countByUserName(userInfo.getUsername()) > 0)
                throw new RuntimeException("用户名为：" + userInfo.getUsername() + "已存在");
            user = new User();
            user.setCreateDate(new Date());
            user.setApplyKey(applyKey);
            user.setPassword(Encryption.encodeByMD5(defaultPassword).toLowerCase());
        }
        user.setUpdateDate(new Date());
        BeanCopyUtils.copy(userInfo, user, currentNoCopy);
        
        userDAO.saveOrUpdate(user);
        
        DefClassPower def = User.class.getAnnotation(DefClassPower.class);
        powerService.saveFieldPowers(applyKey, def.value()[0], def.value()[1], "用户名", "100", new String[] {user.getUsername()});
        return user.getId();
    }

    @Override
    @Transactional
    public void allot(String id, String[] roles, String[] powers, String[] applys)
    {
        User user = userDAO.get(User.class, id);
        if (user == null)
        {
            throw new RuntimeException("授权操作失败， 找不到ID=[" + id + "]的用户");
        }
        //授权角色
        Set<Role> roleSet = new HashSet<Role>();
        if (roles != null)
        {
            for (String rId : roles)
            {
                if (!StringUtils.isNotBlank(rId))
                {
                    continue;
                }
                roleSet.add(userDAO.get(Role.class, Long.parseLong(rId)));
            }
        }
        user.setRoles(roleSet);
        
        //授权用户
        Set<Power> powerSet = new HashSet<Power>();
        //字段权限不再授权里面，所以字段权限要加进入
        if (user.getPowers() != null) {
            for (Power power : user.getPowers()) {
                if (power instanceof Attribute) powerSet.add(power);
            }
        }
        if (powers != null)
        {
            for (String pId : powers)
            {
                if (!StringUtils.isNotBlank(pId))
                {
                    continue;
                }
                powerSet.add(userDAO.get(Power.class, Long.parseLong(pId)));
            }
        }
        user.setPowers(powerSet);
        
        Set<Apply> applis = new HashSet<Apply>();
        if (applys != null) 
        {
            for (String aid : applys)
            {
                if (!StringUtils.isNotBlank(aid))
                {
                    continue;
                }
                applis.add(userDAO.get(Apply.class, Long.parseLong(aid)));
            }
        }
        user.setApplys(applis);
        userDAO.update(user);
    }

    @Override
    public void updatePassword(String username, String oldPassword, String newPassword)
    {
        User user = userDAO.getByProperties(User.class, "username", username);
        if (user == null)
        {
            throw new RuntimeException("修改密码失败，用户【" + username + "】不存在");
        }
        if (!user.getPassword().equalsIgnoreCase(Encryption.encodeByMD5(oldPassword)))
        {
            throw new RuntimeException("旧密码错误");
        }
        user.setPassword(Encryption.encodeByMD5(newPassword).toLowerCase());
        userDAO.update(user);
    }

    @Override
    public void resetPassword(String id, String username)
    {
        User user = userDAO.get(User.class, id);
        user.setPassword(Encryption.encodeByMD5(defaultPassword).toLowerCase());
        user.setUpdateDate(new Date());
        user.setUpdateUser(username);
        userDAO.update(user);
    }
    
    
    @Override
    public UserVO getUserAndPower(String id)
    {
        User user = userDAO.get(User.class, id);
        return copyEntity(user);
    }
    
    /**
     * 
     * 
     * @param user 用户
     * @return UserInfo
     * @see
     */
    private UserVO copyEntity(User user) 
    {
        UserVO info = new UserVO();
        BeanUtils.copyProperties(user, info);
        String powers = "";
        if (user.getPowers() != null)  {
            for (Power power : user.getPowers()) {
                powers += power.getId() + ",";
            }
        }
        info.setPowerstrs(powers);
        
        String roles = "";
        if (user.getRoles() != null) {
            for (Role role : user.getRoles()) {
                roles += role.getId() + ",";
            }
        }
        
        String applystrs = "";
        if (user.getApplys() != null) {
            for (Apply apply : user.getApplys()) {
                applystrs += apply.getId() + ",";
            }
        }
        info.setApplystrs(applystrs);
        info.setRolestrs(roles);
        return info;
    }
    
    @Override
    public void allotField(String userId, Long fieldId)
    {
        UserPowerMapping mapping = new UserPowerMapping(userId, fieldId);
        userDAO.save(mapping);
    }
    
    @Override
    public void deleteField(String userId, Long fieldId)
    {
        UserPowerMapping mapping = userDAO.get(UserPowerMapping.class, new UserPowerKey(userId, fieldId));
        if (mapping != null)
            userDAO.delete(mapping);
    }

    @Override
    public BaseDAO getDAO()
    {
        return userDAO;
    }

}
