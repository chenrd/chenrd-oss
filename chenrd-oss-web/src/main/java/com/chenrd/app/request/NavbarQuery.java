package com.chenrd.app.request;

import com.chenrd.dao.info.QueryInfo;
import com.chenrd.example.Example;
import com.chenrd.example.Status;

/**
 * Created by chenrd on 2017/8/30.
 */
public class NavbarQuery extends Example implements QueryInfo {

    private static final long serialVersionUID = -7695129723992481961L;

    private String title;

    private Integer status = Status.OFF;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getStatus() {
        return status;
    }

    @Override
    public void setStatus(Integer status) {
        this.status = status;
    }
}
