package com.rainpavilion.mapper;

import com.rainpavilion.entity.shiro.Admin;

public interface AdminMapper {
    //注册
    int addAdmin(Admin admin);
    //登录
    Admin selectOne(Admin admin);

}
