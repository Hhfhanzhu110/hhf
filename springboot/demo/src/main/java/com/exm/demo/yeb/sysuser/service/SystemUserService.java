package com.exm.demo.yeb.sysuser.service;

import com.exm.demo.yeb.sysuser.domain.SystemUser;

public interface SystemUserService {

    public SystemUser login(String username, String password);
}
