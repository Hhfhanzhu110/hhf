package com.exm.demo.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * @param securityManager 安全管理器
     * @return ShiroFilterFactoryBean
     * @description
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager);
        Map<String, String> filterMap = new HashMap<>();
        //anon 匿名用户可访问
        //authc 认证用户可访问
        //user rememberMe用户可访问
        //perms对应权限用户可访问
        //role 对应角色可访问
        filterMap.put("/", "anon");
        filterMap.put("/login", "anon");
        filterMap.put("/captcha", "anon");
        filterMap.put("/departs", "anon");
        filterMap.put("/register", "anon");
        filterMap.put("/home", "anon");
        filterMap.put("/**", "authc");//所有路径都拦截

        filterMap.put("/logout", "logout");//固定写法，修改用户信息未未登录状态
        filter.setLoginUrl("/home");
        filter.setUnauthorizedUrl("/login");
        filter.setFilterChainDefinitionMap(filterMap);
        return filter;

    }

    /**
     * 多realm自定义认证器（目的根据认证类型执行指定的realm）
     * @return
     */
    @Bean
    MyModularRealmAuthenticator myModularRealmAuthenticator(){
        return new MyModularRealmAuthenticator();
    }
    /**
     * 配置 securityManager
     *
     * @param userRealm 安全数据
     * @return securityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //securityManager.setAuthenticator(myModularRealmAuthenticator());多realm自定义认证器
        securityManager.setCacheManager(getEhCacheManager());
        securityManager.setSessionManager(getDefaultWebSessionManager());
        //
        /*Collection<Realm> realms = new ArrayList<>();
        realms.add(userRealm());
        realms.add(managerRealm());
        securityManager.setRealms(realms);*/
        securityManager.setRealm(userRealm());
        securityManager.setRememberMeManager(cookieRememberMeManager());
        return securityManager;
    }

    /**
     * 配置realm
     *
     * @return realm
     */
    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return userRealm;
    }
    //配置managerRealm
//    @Bean
//    public ManagerRealm managerRealm() {
//        ManagerRealm managerRealm = new ManagerRealm();
//        managerRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return managerRealm;
//    }

    /**
     * 配置认证时需要的密码加密规则
     * 需要关联realm
     * @return 加密规则
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //加密规则
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //加密次数
        hashedCredentialsMatcher.setHashIterations(1);
        return hashedCredentialsMatcher;
    }

    /*==========================权限注解使用配置start============================*/
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor
                = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
    /*==========================权限注解使用配置end============================*/

    /*==========================权限缓存使用配置start============================*/
    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        ehCacheManager.setCacheManagerConfigFile("classpath:config/shiro_cache.xml");
        return ehCacheManager;
    }
    /*==========================权限缓存使用配置end============================*/

    /*==========================权限session管理配置start============================*/
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(10*60*1000);//5分钟
        return defaultWebSessionManager;
    }
    /*==========================权限session管理配置end============================*/
    /*==========================rememberMe管理配置start============================*/
    //基于cookie
    @Bean
    public CookieRememberMeManager cookieRememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        SimpleCookie cookie = new SimpleCookie("rememberMe");//必须设置名字
        cookie.setMaxAge(30*24*60*60);
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }
    /*==========================rememberMe管理配置end============================*/

}
