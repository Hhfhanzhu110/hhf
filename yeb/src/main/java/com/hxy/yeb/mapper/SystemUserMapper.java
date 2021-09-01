package com.hxy.yeb.mapper;

import com.hxy.yeb.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {

    /**
     * 用户登录
     * @param username 用户名
     * @return
     */
    SysUser login(@Param("username") String username);


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    SysUser queryUserByUsername(String username);

    int saveUser(SysUser user);
}
