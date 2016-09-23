/*
 * 文件名：AttributeDefined.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：xuwenqiang
 * 修改时间：2016年8月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.oss.power;

import com.chenrd.example.VO;

public class AttributeDefined extends VO
{

    /**
     * 意义，目的和功能，以及被用到的地方<br>
     */
    private static final long serialVersionUID = -3876777634798792986L;
    
    private String className;
    
    private String classDefinedName;
    
    private String fieldName;
    
    private String fieldDefinedName;
    
    

    /**
     * 
     */
    public AttributeDefined()
    {
        super();
    }

    /**
     * @param className
     * @param classDefinedName
     * @param fieldName
     * @param fieldDefinedName
     */
    public AttributeDefined(String className, String classDefinedName, String fieldName,
                            String fieldDefinedName)
    {
        super();
        this.className = className;
        this.classDefinedName = classDefinedName;
        this.fieldName = fieldName;
        this.fieldDefinedName = fieldDefinedName;
    }

    /**
     * @return Returns the className.
     */
    public String getClassName()
    {
        return className;
    }

    /**
     * @param className The className to set.
     */
    public void setClassName(String className)
    {
        this.className = className;
    }

    /**
     * @return Returns the classDefinedName.
     */
    public String getClassDefinedName()
    {
        return classDefinedName;
    }

    /**
     * @param classDefinedName The classDefinedName to set.
     */
    public void setClassDefinedName(String classDefinedName)
    {
        this.classDefinedName = classDefinedName;
    }

    /**
     * @return Returns the fieldName.
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * @param fieldName The fieldName to set.
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * @return Returns the fieldDefinedName.
     */
    public String getFieldDefinedName()
    {
        return fieldDefinedName;
    }

    /**
     * @param fieldDefinedName The fieldDefinedName to set.
     */
    public void setFieldDefinedName(String fieldDefinedName)
    {
        this.fieldDefinedName = fieldDefinedName;
    }
    
}
