/*
 * 文件名：UserDAOImpl.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.dao.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.chenrd.dao.abs.AbstractBaseDAO;
import com.chenrd.sys.dao.UserDAO;
import com.chenrd.sys.entity.User;
import com.chenrd.sys.service.Status;
import com.chenrd.sys.service.info.UserInfo;



/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see UserDAOImpl
 * @since
 */
@SuppressWarnings("unchecked")
@Repository("userDAO")
public class UserDAOImpl extends AbstractBaseDAO<User> implements UserDAO {

    @Override
    public Long countByUserName(String username) {
        Map<String, Serializable> params = new HashMap<String, Serializable>();
        StringBuilder hql = new StringBuilder("select count(*) from ").append(User.class.getSimpleName()).append(" as po where po.status>=:status and po.username=:username");
        params.put("status", Status.OFF);
        params.put("username", username);
        return super.count(hql.toString(), params);
    }

	@Override
	public List<UserInfo> findFieldByPowerKey(String key) {
		Map<String, Serializable> params = new HashMap<String, Serializable>();
		StringBuilder hql = new StringBuilder("select new com.chenrd.sys.service.info.UserInfo(po.id, po.name, po.username) from ").append(User.class.getSimpleName()).append(" as po inner join po.powers as power with power.key=:key and power.status=:status where po.status=:status");
		params.put("status", Status.ON);
		params.put("key", key);
		return (List<UserInfo>) super.find(hql.toString(), params);
	}

}
