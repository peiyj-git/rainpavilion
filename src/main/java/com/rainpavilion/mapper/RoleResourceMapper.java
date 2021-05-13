package com.rainpavilion.mapper;

import com.rainpavilion.entity.shiro.RoleResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleResourceMapper {

    List<RoleResource> selectList(@Param("roleName") String roleName);

}
