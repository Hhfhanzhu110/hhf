package com.hxy.yeb.service.impl;

import com.hxy.yeb.domain.SysUser;
import com.hxy.yeb.mapper.SystemUserMapper;
import com.hxy.yeb.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public SysUser login(String username, String password) {
        // 查询用户信息
        SysUser user = systemUserMapper.login(username);
        return user;
    }

    @Override
    public SysUser queryUserByUsername(String username) {
        return systemUserMapper.queryUserByUsername(username);
    }

    @Override
    public int saveUser(SysUser user) {
        String password = passwordEncoder.encode(user.getPassword());
        user.setRealPass(user.getPassword());
        user.setPassword(password);
        user.setSalt(user.getUsername());
        user.setCreateTime(new Date());
        return systemUserMapper.saveUser(user);
    }
}
