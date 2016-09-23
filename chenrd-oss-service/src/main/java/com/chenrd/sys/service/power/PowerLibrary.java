/*
 * 文件名：PowerLibrary.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月14日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.power;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chenrd.sys.service.info.PowerInfo;

/**
 * 
 * 每一个用户都拥有一个权限库
 * @author chenrd
 * @version 2015年5月14日
 * @see PowerLibrary
 * @since
 */
public class PowerLibrary
{
    /**
     * 权限集合
     */
    private Map<String, List<PowerInfo>> powers = new HashMap<String, List<PowerInfo>>();
    
    /**
     * 
     * @param infos
     */
    public PowerLibrary(List<PowerInfo> infos) 
    {
        
    }
    
    
    /**
     * 
     * @param infos List<PowerInfo>
     */
    public PowerLibrary(String name, List<PowerInfo> infos)
    {
        powers.put(name, infos);
    }
    
    /**
     * 
     * 获取一类的权限集合
     * @param name 
     * @return List<PowerInfo>
     * @see
     */
    public List<PowerInfo> get(String name)
    {
        return powers.get(name);
    }
    
    /**
     * 
     * 往指定的权限集合中添加一个权限
     * @param name 
     * @param info 权限
     * @see
     */
    public void add(String name, PowerInfo info)
    {
        List<PowerInfo> list = powers.get(name);
        
        //权限集合不存在，创建一个权限集合
        if (list == null) 
        {
            list = new ArrayList<PowerInfo>();
            powers.put(name, list);
        }
        list.add(info);
    }
    
    /**
     * 
     * 添加多个权限
     * @param name 
     * @param infos List<PowerInfo>
     * @see
     */
    public void addAll(String name, List<PowerInfo> infos)
    {
        List<PowerInfo> list = powers.get(name);
        
        //权限集合不存在，创建一个权限集合
        if (list == null) 
        {
            powers.put(name, infos);
        } 
        else 
        {
            list.addAll(infos);
        }
    }
}
