package com.rainpavilion.mapper;

import com.rainpavilion.entity.shiro.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    Role selectById(@Param("roleId") Integer roleId);
}
