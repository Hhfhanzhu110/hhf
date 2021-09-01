package com.hxy.yeb.config.security.config;

import com.hxy.yeb.config.jwt.JwtAuthTokenFilter;
import com.hxy.yeb.config.security.handler.SysAuthenticationFailureHandler;
import com.hxy.yeb.config.security.handler.SysAuthenticationSuccessHandler;
import com.hxy.yeb.config.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author hanhf
 * @description springsecurity 配置类
 * @date 2021/8/31 3:27 下午
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysAuthenticationSuccessHandler successHandler;

    @Autowired
    private SysAuthenticationFailureHandler failureHandler;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthTokenFilter jwtAuthTokenFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 基于token，所以不需要session,这里设置STATELESS(无状态)是在请求是不生成session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                //配置权限
                .authorizeRequests()
                //对于登录login  验证码captchaImage  允许匿名访问
                .antMatchers("/login").anonymous()
                .antMatchers("/captcha").permitAll()//验证码
                .antMatchers("/system/menu/main/getMenusByUser").permitAll()//验证码
                .antMatchers("/user/register").hasAnyRole("admin")//注册用户(只有管理员可以)
                .antMatchers("/departs").permitAll()//部门信息，用于注册用户使用
                .antMatchers("/system/user", "/system/role", "/system/menu")
                .hasAnyRole("ADMIN")    //admin角色可以访问
                //  除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()//authenticated()要求在执 行该请求时，必须已经登录了应用
                //CSRF禁用，因为不使用session
                .csrf().disable();//禁用跨站csrf攻击防御，否则无法登陆成功
        //登出功能
        httpSecurity.logout().logoutUrl("/logout");
        //  添加JWT  filter, 在每次http请求前进行拦截
        httpSecurity.addFilterBefore(jwtAuthTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //调用DetailsService完成用户身份验证              设置密码加密方式
        auth.userDetailsService(userDetailsService).passwordEncoder(getBCryptPasswordEncoder());
    }


    //在通过数据库验证登录的方式中不需要配置此种密码加密方式, 因为已经在JWT配置中指定
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
    * @description 认证管理器（负责调用userDetailsService）
    * @param
    * @date 2021/8/31 3:45 下午
    * @author hanhf
    * @return org.springframework.security.authentication.AuthenticationManager
    */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
