package com.chenrd.app.vo;

import com.chenrd.common.DateUtil;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.example.VO;

import java.util.Date;

/**
 * Created by chenrd on 2017/8/30.
 */
public class NavbarVO extends VO {

    private static final long serialVersionUID = 5686412587248325721L;

    private Long id;

    private String title;

    private String icon;

    private String linkUrl;

    private Integer sort;

    private String remark;

    private Integer status;

    private String createTime;

    private String updateTime;

    public NavbarVO() {
    }

    @FindConstructor(name = "find", value = "select new com.chenrd.app.vo.NavbarVO(po.id, po.title, po.icon, po.linkUrl, po.sort, po.remark, po.status, po.createTime, po.updateTime) ")
    public NavbarVO(Long id, String title, String icon, String linkUrl, Integer sort, String remark, Integer status, Date createTime, Date updateTime) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.linkUrl = linkUrl;
        this.sort = sort;
        this.remark = remark;
        this.status = status;
        this.createTime = DateUtil.formatDateTime(createTime);
        this.updateTime = DateUtil.formatDateTime(updateTime);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
