package com.rainpavilion.service;


import com.rainpavilion.entity.Menu;

import java.util.List;

public interface MenuService {
    //    后台功能列展示
    List<Menu> selectAll();

    //测试aop add
    void add();

    //测试aop del
    void del();

    //测试aop update
    void update();

    //测试aop 查询
    void select();


}
