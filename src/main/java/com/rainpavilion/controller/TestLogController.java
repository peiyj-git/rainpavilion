package com.rainpavilion.controller;


import com.rainpavilion.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class TestLogController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public void add(){
        menuService.add();
    }

    @PostMapping("/del")
    public void del(){
        menuService.del();
    }

    @PostMapping("/update")
    public void update(){
        menuService.update();
    }

    @PostMapping("/select")
    public void select(){
        menuService.select();
    }

}
