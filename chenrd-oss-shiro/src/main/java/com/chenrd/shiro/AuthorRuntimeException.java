/*
 * 文件名：AuthorRuntimeException.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2015年12月11日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro;

import com.chenrd.common.BaseExecuteException;


/**
 * 身份验证失败
 * 
 * @author chenrd
 * @version 2015年12月11日
 * @see AuthorRuntimeException
 * @since
 */
public class AuthorRuntimeException extends BaseExecuteException
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 1859447279787322524L;
    
    /**
     * 
     */
    public AuthorRuntimeException(String msg)
    {
        super(msg);
    }

}
