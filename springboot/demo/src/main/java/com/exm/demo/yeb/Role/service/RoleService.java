package com.exm.demo.yeb.Role.service;

import java.util.Set;

public interface RoleService {

    Set<String> selectRoleKeys(Long userId);
}
