package com.hxy.yeb.common.utils;

import com.hxy.yeb.domain.SysUser;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

/**
 * spring security 工具类
 */
public class SecurityUtils {

    /**
    * @description 获取当前登录用户
    * @date 2021/9/1 11:16 上午
    * @author hanhf
    * @return com.hxy.yeb.domain.SysUser
    */
    public static SysUser getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            SysUser user = (SysUser) authentication.getPrincipal();
            return user;
        }
        return null;
    }

    /**
    * @description 获取当前登录用户权限
    * @param
    * @date 2021/9/1 11:19 上午
    * @author hanhf
    * @return java.util.List<org.springframework.security.core.GrantedAuthority>
    */
    public static List<GrantedAuthority> getGrantedAuthorities(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
            return authorities;
        }
        return null;
    }






}
