package com.rainpavilion.service.impl;


import com.rainpavilion.config.cache.AddCacheAnnotation;
import com.rainpavilion.config.log.LogAnnotation;
import com.rainpavilion.entity.Menu;
import com.rainpavilion.mapper.MenuMapper;
import com.rainpavilion.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuDao;

    @AddCacheAnnotation
    @Override
    public List<Menu> selectAll() {

        return menuDao.selectAll();
    }

    @LogAnnotation(content="测试aop添加数据",type="add")
    @Override
    public void add() {
        System.err.println("-----测试aop添加数据");
    }

    @LogAnnotation(content="测试aop删除数据",type="del")
    @Override
    public void del() {
        System.err.println("-----测试aop删除数据");
    }

    @LogAnnotation(content="测试aop修改数据",type="update")
    @Override
    public void update() {
        System.err.println("-----测试aop修改数据");
    }

    @LogAnnotation(content="测试aop查询数据",type="select")
    @Override
    public void select() {
        System.err.println("-----测试aop查询数据");
    }


}
