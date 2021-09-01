package com.hxy.yeb.service;

import java.util.Set;

public interface RoleService {

    Set<String> selectRoleKeys(Long userId);
}
