package com.exm.demo.yeb.login.service.impl;

import com.exm.demo.yeb.login.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Override
    public void login(String username, String password) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //MyToken token = new MyToken(username,password,loginType);
        //token.setRememberMe(rememberMe);
        subject.login(token);
        return;
    }
}
