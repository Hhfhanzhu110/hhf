package com.exm.demo.yeb.user.mapper;

import com.exm.demo.yeb.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {

    /**
     * 用户登录
     * @param username 用户名
     * @return
     */
    User login(@Param("username") String username);


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User queryUserByUsername(String username);

    int saveUser(User user);
}
