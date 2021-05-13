package com.rainpavilion.entity.shiro;

import lombok.Data;
import lombok.ToString;

//角色表
@Data
@ToString
public class Role {
    //角色id
    private Integer RoleId;
    //角色名字
    private String RoleName;
}
