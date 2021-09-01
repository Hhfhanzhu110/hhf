package com.hxy.yeb.mapper;

import com.hxy.yeb.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface RoleMapper {

    List<Role> selectRoleKeys(Long userId);
}
