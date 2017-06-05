/*
 * 文件名：LogRecord.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年5月20日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.sys.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.chenrd.example.Domain;


/**
 * 日志记录类
 * @author chenrd
 * @version 2015年5月20日
 * @see LogRecord
 * @since
 */
@Entity
@Table(name = "OSS_LOGRECORD")
@NamedQueries({@NamedQuery(name = "logList", query = "select new com.chenrd.sys.service.info.LogInfo(t.id,t.type,t.createDate,t.userName,t.applyKey,t.label) from com.chenrd.sys.entity.LogRecord as t where t.applyKey=:applyKey and t.type=:type order by t.createDate desc")})
public class LogRecord extends Domain {

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = 1095901750902347091L;
    
    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 应用KEY
     */
    @Column(name = "APPLY_KEY", length = 20)
    private String applyKey;
    
    /**
     * 模块信息
     */
    @Column(name = "LABEL", length = 100)
    private String label;
    
    /**
     * 0:登陆日志
     * 1:操作日志
     * 2:异常日志
     */
    @Column(name = "TYPE_", length = 1)
    private int type;
    
    /**
     * 创建时间
     */
    @Column(name = "CREATE_DATE")
    private Date createDate = new Date();
    
    /**
     * 用户名称
     */
    @Column(name = "USER_NAME", length = 50)
    private String userName;
    
    /**
     * 内容
     */
    @Column(name = "CONTENT", length = 2000)
    private String content;
    
    
    /**
     * 
     */
    public LogRecord() {
        
    }

    /**
     * @param id 
     * @param type 
     * @param createDate 
     * @param userName 
     */
    public LogRecord(int type, Date createDate, String userName) {
        super();
        this.type = type;
        this.createDate = createDate;
        this.userName = userName;
    }

	/**
	 * @return Returns the id.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id The id to set.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return Returns the applyKey.
	 */
	public String getApplyKey() {
		return applyKey;
	}

	/**
	 * @param applyKey The applyKey to set.
	 */
	public void setApplyKey(String applyKey) {
		this.applyKey = applyKey;
	}

	/**
	 * @return Returns the label.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label The label to set.
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return Returns the createDate.
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate The createDate to set.
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return Returns the userName.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName The userName to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return Returns the content.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content The content to set.
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
