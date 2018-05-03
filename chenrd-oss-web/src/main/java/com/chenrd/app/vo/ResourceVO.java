package com.chenrd.app.vo;

import com.chenrd.common.DateUtil;
import com.chenrd.dao.annotation.FindConstructor;
import com.chenrd.example.VO;

import java.util.Date;

/**
 * Created by chenrd on 2017/8/27.
 */
public class ResourceVO extends VO {

    private static final long serialVersionUID = 8584093310550813277L;

    private Long id;

    private String type;

    private String url;

    private String path;

    private String createTime;

    public ResourceVO() {
    }

    @FindConstructor(name = "find", value = "select new com.chenrd.app.vo.ResourceVO(po.id, po.type, po.url, po.path, po.createTime) ")
    public ResourceVO(Long id, String type, String url, String path, Date createTime) {
        this.id = id;
        this.type = type;
        this.url = url;
        this.path = path;
        this.createTime = DateUtil.formatDateTime(createTime);
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
