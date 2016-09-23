/*
 * 文件名：LogInfo.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.service.info;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @author chenrd
 * @version 2015年5月20日
 * @see LogInfo
 * @since
 */
public class LogInfo implements Serializable
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -6480206335904126999L;
    
    /**
     * 登陆日志
     */
    public static final int TYPE_LOGIN = 0;
    
    /**
     * 操作日志
     */
    public static final int TYPE_HLEP = 1;
    
    /**
     * 异常日志
     */
    public static final int TYPE_ERROR = 2;
    
    /**
     * 
     */
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    
    /**
     * 
     */
    private Long id;
    
    /**
     * 
     */
    private String applyKey;
    
    /**
     * 
     */
    private String label;
    
    /**
     * 
     */
    private int type;
    
    /**
     * 创建时间
     */
    private String createDate;
    
    /**
     * 用户名称
     */
    private String userName;
    
    /**
     * 内容
     */
    private String content;

    /**
     * @return Returns the id.
     */
    public Long getId()
    {
        return id;
    }
    
    

    /**
     * 
     */
    public LogInfo()
    {
        super();
    }
    
    



    /** 
     * @param id 
     * @param type 
     * @param createDate 
     * @param userName 
     * @param applyKey 
     * @param label
     */
    public LogInfo(Long id, int type, Date createDate, String userName, String applyKey, String label)
    {
        super();
        this.id = id;
        this.type = type;
        this.createDate = createDate == null ? null : format.format(createDate);
        this.userName = userName;
        this.applyKey = applyKey;
        this.label = label;
    }
    
    /** 
     * @param id 
     * @param type 
     * @param createDate 
     * @param userName 
     * @param applyKey 
     * @param label
     */
    public LogInfo(Long id, int type, Date createDate, String userName, String applyKey, String label, String content)
    {
        super();
        this.id = id;
        this.type = type;
        this.createDate = createDate == null ? null : format.format(createDate);
        this.userName = userName;
        this.applyKey = applyKey;
        this.label = label;
        this.content = content;
    }
    
    /** 
     * @param id 
     * @param type 
     * @param createDate 
     * @param userName 
     * @param applyKey 
     * @param label
     */
    public LogInfo(int type, Date createDate, String userName, String applyKey, String label, String content)
    {
        super();
        this.type = type;
        this.createDate = createDate == null ? null : format.format(createDate);
        this.userName = userName;
        this.applyKey = applyKey;
        this.label = label;
        this.content = content;
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
     * @return Returns the userName.
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param userName The userName to set.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return Returns the content.
     */
    public String getContent()
    {
        return content;
    }

    /**
     * @param content The content to set.
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * @return Returns the serialversionuid.
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return Returns the applyKey.
     */
    public String getApplyKey()
    {
        return applyKey;
    }

    /**
     * @param applyKey The applyKey to set.
     */
    public void setApplyKey(String applyKey)
    {
        this.applyKey = applyKey;
    }

    /**
     * @return Returns the label.
     */
    public String getLabel()
    {
        return label;
    }

    /**
     * @param label The label to set.
     */
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    

}
