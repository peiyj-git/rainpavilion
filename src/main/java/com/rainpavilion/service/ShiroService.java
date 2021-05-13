package com.rainpavilion.service;

import java.util.Set;

public interface ShiroService {
    /**
     * 根据管理员的用户名 查询拥有的所有角色的角色名
     * 返回值类型 Set<String> Set集合中的值是角色名
     * @param name
     * @return
     */
    Set<String> selectRoleNameByUsername(String name);

    /**
     * 根据管理员的用户名 查询拥有的所有权限的权限字符串
     * 返回值类型 Set<String> Set集合中的值是权限字符串
     * @param name
     * @return
     */
    Set<String> selectPermissionByUsername(String name);
}
