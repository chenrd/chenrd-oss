package com.chenrd.app.entity;

import com.chenrd.example.Domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by chenrd on 2017/8/24.
 */
@Entity
@Table(name = "ad_resource")
public class Resource extends Domain {

    private static final long serialVersionUID = -7850940464272266327L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "type", length = 32)
    private String type;

    @Column(name = "url", length = 256)
    private String url;

    @Column(name = "path", length = 128)
    private String path;

    @Column(name = "create_time", nullable = false)
    private Date createTime;

    enum Type {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
