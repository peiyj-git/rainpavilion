package com.rainpavilion.entity.shiro;

import lombok.Data;
import lombok.ToString;

//角色权限表
@Data
@ToString
public class RoleResource {
    //角色权限id
    private Integer id;
    //角色名字
    private String RoleName;
    //权限id
    private Integer ResourceId;
}
