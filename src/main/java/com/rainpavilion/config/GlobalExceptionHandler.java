package com.rainpavilion.config;

import com.rainpavilion.entity.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ControllerAdvice 声明当前类是一个控制器 同时具有异常捕获的能力
 *
 * 开发时候 不要使用
 *
 * 上线之后可以使用
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获异常 响应一句话(json)给页面
     *
     * 要捕获那个异常  @ExceptionHandler
     *
     * 获取异常信息 怎么获取 方法形参
     */
    //可以响应一句话 也可以跳转一个页面 (返回值 给String类型)
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Response exceptionHandlerTest(Exception e){
        log.warn(e.getMessage());
        log.error(e.getMessage());
        log.error("不存在权限的管理员在尝试启动权限");
        return Response.failed(e.getMessage());
    }
}
