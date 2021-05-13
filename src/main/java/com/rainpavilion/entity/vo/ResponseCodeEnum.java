package com.rainpavilion.entity.vo;

public enum ResponseCodeEnum {
    SUCCESS("200","操作成功")
    ,FAILED("400","操作失败")
    ,INVALID_MD5("400","签名校验失败")
    ,INVALID_PARAM_MD5("400","签名参数不能为空")
    ,INVALID_ACCESS("400","非法访问")
    ,SESSION_INVALID_OR_EXPIRED("403","Session无效或已过期")
    ,SESSION_INVALID_RSA("403","sessionId解密失败")
    ,TOKEN_NOT_EXIST("403","token信息不存在")
    ,VERIFY_CODE_ERROR("600","验证码输入有误")
    ,INVALID_PARAMS("400","接口参数校验失败")
    ,REQUST_METHOD_INVALID("400","请求方式错误")
    ,THREAD_POOL_ERROR("500","线程池队列已满,任务拒绝执行")
    ,SERVER_INTERNAL_ERROR("500","系统繁忙,请稍后再试")
    ,MEDIA_NOTEXISTS_ERROR("501","无视频信息");

    private String code;
    private String desc;

    ResponseCodeEnum(String code, String desc){
        this.code = code;
        this.desc = desc;
    }
    public String code(){
        return code;
    }
    public String desc(){
        return desc;
    }
}























