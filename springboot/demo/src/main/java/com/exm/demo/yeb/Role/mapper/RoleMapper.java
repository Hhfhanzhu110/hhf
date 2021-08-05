package com.exm.demo.yeb.Role.mapper;

import com.exm.demo.yeb.Role.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {

    List<Role> selectRoleKeys(Long userId);
}
