package com.exm.demo.yeb.user.service;

import com.exm.demo.yeb.user.domain.User;

public interface SystemUserService {

    public User login(String username, String password);

    public User queryUserByUsername(String username);

    public int saveUser(User user);
}
