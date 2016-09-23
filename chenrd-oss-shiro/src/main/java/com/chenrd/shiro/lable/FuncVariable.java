/*
 * 文件名：FuncVariable.java
 * 描述：
 * 修改人：chenrd
 * 修改时间：2015年7月29日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.lable;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chenrd.shiro.ehcache.UserEhcacheHandle;
import com.chenrd.spring.SpringBeanUtil;
import com.chenrd.sys.service.info.PowerInfo;
import com.chenrd.sys.service.info.UserInfo;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 生成按钮<a class="btn btn-default purple" id="newapplybtn" href="javascript:void(0);"><i class="fa fa-plus"></i> 添加应用</a>
 * 参数：aClass、id、type、name、iClass
 * @author chenrd
 * @version 2015年7月29日
 * @see FuncVariable
 * @since
 */
public class FuncVariable implements TemplateDirectiveModel
{

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body)
        throws TemplateException, IOException
    {
        String url = ((SimpleScalar) params.get("url")).getAsString(),
            aClass = ((SimpleScalar) params.get("aClass")).getAsString(),
            iClass = ((SimpleScalar) params.get("iClass")).getAsString(),
            id = params.get("id") == null ? null : ((SimpleScalar) params.get("id")).getAsString(),
            dataTarget = params.get("dataTarget") == null ? null : ((SimpleScalar) params.get("dataTarget")).getAsString(),
            title = params.get("title") == null ? null : ((SimpleScalar) params.get("title")).getAsString();
        
        UserEhcacheHandle userEhcacheHandle = (UserEhcacheHandle) SpringBeanUtil.getObject("userEhcacheHandle");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest request = attr.getRequest();
        UserInfo userInfo = userEhcacheHandle.get(request.getRequestedSessionId());
        Map<String, PowerInfo> powers = userInfo.getPowers();
        StringBuilder btnStr = new StringBuilder();
        PowerInfo info = null;
        if ((info = powers.get(url)) != null) {
            btnStr.append("<a href=\"javascript:void(0)\"");
            if (StringUtils.isNotBlank(aClass)) btnStr.append(" class=\"").append(aClass).append("\"");
            if (StringUtils.isNotBlank(aClass) && aClass.indexOf("btn-circle") != -1) btnStr.append(" title=\"").append(info.getName()).append("\"");
            if (StringUtils.isNotBlank(id)) btnStr.append(" id=\"").append(id).append("\"");
            if (StringUtils.isNotBlank(title)) btnStr.append(" title=\"").append(title).append("\"");
            if (StringUtils.isNotBlank(dataTarget)) btnStr.append(" data-target=\"").append(dataTarget).append("\"");
            btnStr.append("><i class=\"").append(iClass).append("\"></i> ").append((StringUtils.isNotBlank(aClass) && aClass.indexOf("btn-circle") != -1 ? "" : info.getName())).append("</a>");
        }
        
        Writer out = env.getOut();
        out.write(btnStr.toString());
    }
    
}
