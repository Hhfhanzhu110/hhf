package com.exm.demo.yeb.Role.service.impl;

import com.exm.demo.yeb.Role.domain.Role;
import com.exm.demo.yeb.Role.mapper.RoleMapper;
import com.exm.demo.yeb.Role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public Set<String> selectRoleKeys(Long userId) {
        Set<String> roleSet = new HashSet<>();
        List<Role> roles = roleMapper.selectRoleKeys(userId);
        roles.forEach(role -> {
            if (role != null){
                roleSet.addAll(Arrays.asList(role.getRoleKey().trim().split(",")));
            }
        });
        return roleSet;
    }
}
