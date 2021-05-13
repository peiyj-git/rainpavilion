package com.rainpavilion.utils;


import java.io.Serializable;


public class ShopResult implements Serializable {


    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ShopResult build(Integer status, String msg, Object data) {
        return new ShopResult(status, msg, data);
    }

    public static ShopResult ok(Object data) {
        return new ShopResult(data);
    }

    public static ShopResult ok() {
        return new ShopResult(null);
    }

    public ShopResult() {

    }

    public static ShopResult build(Integer status, String msg) {
        return new ShopResult(status, msg, null);
    }

    public ShopResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ShopResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
