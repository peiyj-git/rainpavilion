package com.rainpavilion.controller;


import com.rainpavilion.entity.Menu;
import com.rainpavilion.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

//    @RequestMapping("/selectAll")
//    public List<Menu> selectAll(){
//
//        /**
//         * 代码式授权案例：用户具有 超级管理员的角色 才能查询菜单数据
//         * 没有return null
//         */
//        Subject subject = SecurityUtils.getSubject();
//
//        if (subject.hasRole("superadmin")) {
//            return menuService.selectAll();
//        }else {
//            return null;
//        }
//
////        return menuService.selectAll();
//    }


    //校验角色
//    @RequiresRoles("superadmin")

    //校验权限
    //具有某个权限才能调用改方法 没有权限会报错
    @RequiresPermissions("menu:show")
    @RequestMapping("/selectAll")
    public List<Menu> selectAll(){
        return menuService.selectAll();
    }


}
