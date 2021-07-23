package com.exm.demo.utils;

import com.exm.demo.yeb.sysuser.domain.SystemUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

/**
 * 保存和获取当前用户的工具类
 * Created by lenovo on 2020/1/16.
 */
public class UserContext {
    private static final String CURRENT_USER_IN_SESSION = "systemUser";
    /**
     * 得到session
     */
    private static HttpSession getSession(){
        //SpringMVC获取session的方式通过RequestContextHolder
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
    }
    /**
     * 设置当前用户到session中
     */
    public static void putCurrebtUser(SystemUser currentUser) {
        getSession().setAttribute(CURRENT_USER_IN_SESSION,currentUser);
    }
    /**
     * 获取当前用户
     */
    public static SystemUser getCurreentUser() {
        return (SystemUser) getSession().getAttribute(CURRENT_USER_IN_SESSION);
    }
}

