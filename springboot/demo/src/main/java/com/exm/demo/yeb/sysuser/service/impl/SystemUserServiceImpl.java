package com.exm.demo.yeb.sysuser.service.impl;

import com.exm.demo.yeb.sysuser.mapper.SystemUserMapper;
import com.exm.demo.yeb.sysuser.domain.SystemUser;
import com.exm.demo.yeb.sysuser.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    SystemUserMapper systemUserMapper;

    @Override
    public SystemUser login(String username, String password) {
        return systemUserMapper.login(username,password);
    }
}
