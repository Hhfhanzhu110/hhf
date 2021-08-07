package com.exm.demo.config.shiro;

import org.apache.shiro.authc.UsernamePasswordToken;

//多个realm不使用
public class MyToken extends UsernamePasswordToken {
    private String loginType;

    public MyToken(String username,String password,String loginType){
        super(username,password);
        this.loginType = loginType;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
