package com.chenrd.common;

import com.chenrd.constant.Constant;

/**
 * Created by chenrd on 2017/8/27.
 */
public class WebResult {

    private int statusCode = 200;

    private String msg;

    enum WebResultType {
        SUCCESS(Constant.WEB_SUCCESS, "success")

        ;

        private int code;

        private String msg;

        WebResultType(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
