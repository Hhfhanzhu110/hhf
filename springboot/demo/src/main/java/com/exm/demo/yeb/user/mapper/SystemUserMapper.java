package com.exm.demo.yeb.user.mapper;

import com.exm.demo.yeb.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public User login(@Param("username") String username, @Param("password") String password);


}
