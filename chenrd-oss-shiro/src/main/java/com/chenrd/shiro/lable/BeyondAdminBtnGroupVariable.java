/*
 * 文件名：BeyondAdminBtnGroupVariable.java
 * 版权：Copyright by www.huawei.com
 * 描述：
 * 修改人：chenrd
 * 修改时间：2016年12月15日
 * 跟踪单号：
 * 修改单号：
 * 修改内容：
 */

package com.chenrd.shiro.lable;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

public class BeyondAdminBtnGroupVariable implements TemplateDirectiveModel {

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		Writer out = env.getOut();
		UserEhcacheHandle userEhcacheHandle = (UserEhcacheHandle) SpringBeanUtil.getObject("userEhcacheHandle");
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();  
        HttpServletRequest request = attr.getRequest();
        UserInfo userInfo = userEhcacheHandle.get(request.getRequestedSessionId());
        Map<String, PowerInfo> powers = userInfo.getPowers();
        String keys = ((SimpleScalar) params.get("keys")).getAsString();
        StringBuilder listr = new StringBuilder();
        for (String url : keys.split(",")) {
        	if (powers.containsKey(url)) {
        		listr.append("<li><a href=\"javascript:void(0);\" id=\"").append(url.substring(url.lastIndexOf("/") + 1)).append("\">").append(powers.get(url).getName()).append("</a></li>");
        	}
        }
        if (listr.length() == 0) out.write("");
        else {
        	StringBuilder btnStr = 
                	new StringBuilder("<div class=\"btn-group right\">")
                			.append("<button type=\"button\" class=\"btn btn-default dropdown-toggle purple\" data-toggle=\"dropdown\">")
                				.append("<i class=\"fa fa-ellipsis-horizontal\"></i><i class=\"fa fa-angle-down\"> ").append(((SimpleScalar) params.get("name")).getAsString()).append("</i>")
                			.append("</button>")
                			.append("<ul class=\"dropdown-menu\">")
                			.append(listr)
                			.append("</ul></div>");
        	out.write(btnStr.toString());
        }
	}

}
