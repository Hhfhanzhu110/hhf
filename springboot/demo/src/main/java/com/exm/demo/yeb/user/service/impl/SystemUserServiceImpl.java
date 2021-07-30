package com.exm.demo.yeb.user.service.impl;

import com.exm.demo.yeb.user.mapper.SystemUserMapper;
import com.exm.demo.yeb.user.domain.User;
import com.exm.demo.yeb.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    SystemUserMapper systemUserMapper;

    @Override
    public User login(String username, String password) {
        return systemUserMapper.login(username,password);
    }
}
