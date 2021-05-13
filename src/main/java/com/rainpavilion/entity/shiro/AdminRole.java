package com.rainpavilion.entity.shiro;

import lombok.Data;
import lombok.ToString;

//管理员角色表
@Data
@ToString
public class AdminRole {
    //id
    private Integer id;
    //管理员id
    private Integer adminId;
    //角色表id
    private Integer roleId;
}
