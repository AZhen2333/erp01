package com.dg.entity;

import com.dg.utils.IStatusMessage;

import java.io.Serializable;

/**
 * 前端请求响应结果,code:编码,message:描述,obj对象，可以是单个数据对象，数据列表或者PageInfo
 */
public class ResponseResult implements Serializable {
    private static final long serialVersionUID = 7285065610386199394L;

    private String code;
    private String message;
    private Object obj;

    /**
     * 成功
     */
    public ResponseResult() {
        this.code = IStatusMessage.SystemStatus.SUCCESS.getCode();
        this.message = IStatusMessage.SystemStatus.SUCCESS.getMessage();
    }

    /**
     * 失败
     *
     * @param statusMessage
     */
    public ResponseResult(IStatusMessage statusMessage) {
        this.code = statusMessage.getCode();
        this.message = statusMessage.getMessage();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
