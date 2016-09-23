/*
 * 文件名：Func.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年6月16日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.chenrd.sys.service.PowerType;

/**
 * 
 * @author chenrd
 * @version 2015年6月16日
 * @see Func
 * @since
 */
@Entity
@DiscriminatorValue("1")
public class Func extends Power
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 7621310261804429559L;
    
    /**
     * 
     */
    public Func()
    {
        super();
        super.setType(PowerType.FUNC_POWER);
    }

}
