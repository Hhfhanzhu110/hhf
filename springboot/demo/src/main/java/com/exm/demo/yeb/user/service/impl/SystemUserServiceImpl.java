package com.exm.demo.yeb.user.service.impl;

import com.exm.demo.common.constant.UserConstants;
import com.exm.demo.common.domain.UserStatus;
import com.exm.demo.common.exception.UserBlockedException;
import com.exm.demo.common.exception.UserNotExistsException;
import com.exm.demo.common.exception.UserPasswordNotMatchException;
import com.exm.demo.utils.Md5SaltUtil;
import com.exm.demo.yeb.user.domain.User;
import com.exm.demo.yeb.user.mapper.SystemUserMapper;
import com.exm.demo.yeb.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    SystemUserMapper systemUserMapper;

    @Override
    public User login(String username, String password) {
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }

        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH) {
            throw new UserPasswordNotMatchException();
        }

        // 查询用户信息
        User user = systemUserMapper.login(username);
        if (user == null) {
            throw new UserNotExistsException();
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus())) {
            throw new UserBlockedException(user.getRemark());
        }
        return user;
    }

    @Override
    public User queryUserByUsername(String username) {
        return systemUserMapper.queryUserByUsername(username);
    }

    @Override
    public int saveUser(User user) {
        String password = Md5SaltUtil.encoderPassword(user.getPassword(), user.getUsername());
        user.setRealPass(user.getPassword());
        user.setPassword(password);
        user.setSalt(user.getUsername());
        user.setCreateTime(new Date());
        return systemUserMapper.saveUser(user);
    }
}
