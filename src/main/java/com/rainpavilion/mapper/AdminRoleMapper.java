package com.rainpavilion.mapper;

import com.rainpavilion.entity.shiro.AdminRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminRoleMapper {

    //查询
    List<AdminRole> selectList(@Param("adminId") Integer adminId);

}
