package com.exm.demo.yeb.sysuser.mapper;

import com.exm.demo.yeb.sysuser.domain.SystemUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SystemUserMapper {

    /**
     * 用户登录
     * @param username 用户名
     * @param encode 密码
     * @return
     */
    public SystemUser login(@Param("username") String username, @Param("password") String encode);


}
