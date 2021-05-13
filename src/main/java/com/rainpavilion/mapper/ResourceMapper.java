package com.rainpavilion.mapper;

import com.rainpavilion.entity.shiro.Resource;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper {
    Resource selectOne(@Param("resourceId") Integer resourceId);
}
