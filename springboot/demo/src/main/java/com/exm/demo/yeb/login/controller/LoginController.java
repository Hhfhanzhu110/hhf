package com.exm.demo.yeb.login.controller;

import com.exm.demo.entity.AxiosResult;
import com.exm.demo.yeb.login.service.LoginService;
import com.exm.demo.yeb.user.domain.User;
import com.exm.demo.yeb.user.service.SystemUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    SystemUserService systemUserService;
    @Autowired
    LoginService loginService;

    /**
     * 登录控制
     */
    @PostMapping("/login")
    @ResponseBody
    public AxiosResult login(String username, String password, String code, HttpServletRequest request) throws JsonProcessingException {
        //校验验证码
        String verifyCode = request.getSession().getAttribute("VerifyCode").toString();
        if (StringUtils.isBlank(verifyCode)) {
            return AxiosResult.error("验证码错误过期");
        } else if (!code.equalsIgnoreCase(verifyCode)) {
            return AxiosResult.error("验证码错误");
        }
        //校验用户
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            //多个realm执行指定的realm
            //MyToken token = new MyToken(username,password,loginType);
            //记住我功能
            //token.setRememberMe(rememberMe);
            subject.login(token);
        } catch (AuthenticationException e) {
            return AxiosResult.error("用户密码错误");
        }
        Map<String,Object> map = new HashMap<>();
        String tokenStr = UUID.randomUUID().toString();
        ObjectMapper mapper = new ObjectMapper();
        String user = mapper.writeValueAsString((User)subject.getPrincipal());
        map.put("tokenStr",tokenStr);
        map.put("systemUser",user);
        return AxiosResult.success("登录成功",map);
    }

    @GetMapping("/")
    public void logout() {
    }

    @PostMapping("/register")
    @ResponseBody
    public AxiosResult register(User user) {
        User userOld = systemUserService.queryUserByUsername(user.getUsername());
        if (userOld == null) {
            int n = systemUserService.saveUser(user);
            return AxiosResult.success("注册成功");
        }
        return AxiosResult.error("该用户已存在");
    }

}
