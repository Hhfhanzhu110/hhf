package com.hxy.yeb.service;


import com.hxy.yeb.domain.SysUser;

public interface SystemUserService {

    public SysUser login(String username, String password);

    public SysUser queryUserByUsername(String username);

    public int saveUser(SysUser user);
}
