package com.rainpavilion.service;

import com.rainpavilion.entity.vo.Response;
import com.rainpavilion.entity.dto.AdminDTO;

public interface AdminService {

    int addAdmin(AdminDTO adminDTO);     //添加

    Response selectOne(AdminDTO adminDTO);           //登录判断

//    Response selectUser(String userPhone, String userPassword);        //登录判断



//    void del(int id);         //删除
//    Admin selectOne(int id);   //查询单条数据
//    void update(Admin user);   //修改
//    Map<String,Object> selectAll(String username, int page, int limit);   //查询全部
//
//    //管理员角色表
//    List<Admin> selectAllroleName(String username, int page, int limit);   //查询全部
//    int selectAllroleNamecount(String username);                          //查询总条数

}

