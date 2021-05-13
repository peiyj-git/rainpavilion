package com.rainpavilion.entity.shiro;

import lombok.Data;
import lombok.ToString;

//权限表
@Data
@ToString
public class Resource {
    //权限id
    private Integer resourceId;
    //权限名字
    private String resourceName;
    //权限地址
    private String resourceUrl;
    //权限类型
    private String resourceType;
    //
    private String resourcePermission;
    //权限父id
    private Integer resourceParentId;
}
