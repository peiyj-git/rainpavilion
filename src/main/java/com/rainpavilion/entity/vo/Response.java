package com.rainpavilion.entity.vo;


import lombok.Data;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;

@Data
@ToString
public class Response {

    private static final String X_CODE = "X-Code";
    private static final String X_DESC = "X-Desc";

    private static final Response SUCCESS = new Response(ResponseCodeEnum.SUCCESS);

    private static final Response FAILED = new Response(ResponseCodeEnum.FAILED);

    private String code;

    private String message;

    private Object data;

    public Response(String code, String message){
        this.code = code;
        this.message = message;
    }

    public Response(String code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Response(ResponseCodeEnum codeEnum){
        this.code = codeEnum.code();
        this.message = codeEnum.desc();
    }

    public Response(ResponseCodeEnum codeEnum, Object data){
        this(codeEnum);
        this.data = data;
    }

    public static Response success(){
        return SUCCESS;
    }

    public static Response success(Object data){
        return new Response(ResponseCodeEnum.SUCCESS,data);
    }

    public static Response failed(){
        return FAILED;
    }

    public static Response failed(String message){
        return new Response(ResponseCodeEnum.SERVER_INTERNAL_ERROR.code(),message);
    }

    public static Response failed(ResponseCodeEnum codeEnum,Object data){
        return new Response(codeEnum,data);
    }

    public static Response failed(ResponseCodeEnum data){
        return new Response(ResponseCodeEnum.FAILED,data.desc());
    }

    public static Response getSuccess(){
        return SUCCESS;
    }

    public static Response getFailed(){
        return FAILED;
    }

    public String getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data = data;
    }

    public void setHeader(HttpServletResponse httpServletResponse){
        httpServletResponse.setHeader(X_CODE,code);
        httpServletResponse.setHeader(X_DESC,message);
    }
}


















