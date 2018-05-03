package com.chenrd.app.entity;

import com.chenrd.dao.annotation.QueryParams;
import com.chenrd.dao.em.Nexus;
import com.chenrd.example.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenrd on 2017/8/30.
 */
@Entity
@Table(name = "ad_navbar")
public class Navbar extends Domain {

    private static final long serialVersionUID = 3287083775676171327L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @QueryParams
    @Column(name = "title", length = 8, nullable = false)
    private String title;

    @Column(name = "icon", length = 128, nullable = false)
    private String icon;

    @Column(name = "link_url", length = 256, nullable = false)
    private String linkUrl;

    @Column(name = "sort", length = 3, nullable = false)
    private Integer sort;

    @Column(name = "remark", length = 256)
    private String remark;

    @QueryParams(nexus = Nexus.GTEQUAL)
    @Column(name = "status", nullable = false, length = 1)
    private Integer status;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(name = "update_time", nullable = false)
    private Date updateTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
