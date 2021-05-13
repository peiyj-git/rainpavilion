package com.rainpavilion.service.impl;

import com.rainpavilion.entity.shiro.*;
import com.rainpavilion.mapper.*;
import com.rainpavilion.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Set<String> selectRoleNameByUsername(String name) {
        Admin adm = new Admin();
//        adm.setAdminName(name);
        adm.setAdminPhone(name);
        Admin admin = adminMapper.selectOne(adm);
        List<AdminRole> adminRoles
                = adminRoleMapper.selectList(admin.getAdminId());

        Set<String> roles = new HashSet<>();
        for (AdminRole adminRole : adminRoles) {
            Role role = roleMapper.selectById(adminRole.getRoleId());
            roles.add(role.getRoleName());
        }
        return roles;
    }

    @Override
    public Set<String> selectPermissionByUsername(String name) {
        Set<String> allRolesByUsername = selectRoleNameByUsername(name);
        Set<String> permissions = new HashSet<>();
        for (String roleName : allRolesByUsername) {
            List<RoleResource> roleResources = roleResourceMapper.selectList(roleName);
            roleResources.forEach(System.out::println);
            for (RoleResource roleResource : roleResources) {
                Resource resource = resourceMapper.selectOne(roleResource.getResourceId());
                if (resource != null) {
                    permissions.add(resource.getResourcePermission());
                }
            }
        }
        return permissions;
    }
}
