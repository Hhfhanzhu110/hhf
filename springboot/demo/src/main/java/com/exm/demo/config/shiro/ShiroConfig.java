package com.exm.demo.config.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        Map<String,String> filterMap = new HashMap<>();
        //anon 匿名用户可访问
        //authc 认证用户可访问
        //user rememberMe用户可访问
        //perms对应权限用户可访问
        //role 对应角色可访问
        filterMap.put("/","anon");
        filterMap.put("/login","anon");
        filterMap.put("/captcha","anon");
        filterMap.put("/**","authc");//所有路径都拦截
        filter.setLoginUrl("/home");
        filter.setUnauthorizedUrl("/login");
        filter.setFilterChainDefinitionMap(filterMap);
        return filter;

    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }
}
