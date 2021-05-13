package com.rainpavilion.config.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 元注解  加在自定义注解上的注解
 * @Target(ElementType.METHOD) 定义当前注解可以加在什么地方 类上？方法上？属性上？
 *   METHOD 方法上  TYPE 类上  FIELD 属性上
 *
 * @Retention(RetentionPolicy.RUNTIME) 定义注解生效的时间
 *
 * SOURCE 定义 java代码---》class文件过程中生效 如果代码编译为了class文件 注解会被jvm删除
 * RUNTIME 一直生效
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {
    /**
     * 操作内容 content
     * 操作类型 type
     */
    String content();

    String type();
}
