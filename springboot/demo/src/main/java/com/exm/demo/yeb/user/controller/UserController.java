package com.exm.demo.yeb.user.controller;

import com.exm.demo.entity.AxiosResult;
import com.exm.demo.utils.UserContext;
import com.exm.demo.yeb.user.domain.User;
import com.exm.demo.yeb.user.service.SystemUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SystemUserService systemUserService;

    /**
     * 登录控制
     */
    @PostMapping("/login")
    @ResponseBody
    public AxiosResult login(String username, String password,String code,HttpServletRequest request) throws JsonProcessingException {
        //校验验证码
        String verifyCode = request.getSession().getAttribute("VerifyCode").toString();
        if (StringUtils.isBlank(verifyCode)){
            return AxiosResult.error("验证码错误过期");
        } else if (!code.equals(verifyCode)){
            return AxiosResult.error("验证码错误");
        }
        //校验用户名和密码
        User systemUser = systemUserService.login(username, password);
        if(systemUser==null){
            return AxiosResult.error("用户名或密码错误");
        }else{
            UserContext.putCurrebtUser(systemUser);
            Map<String, Object> map = new HashMap<>();
            map.put("tokenStr",systemUser.getUserId().toString());
            ObjectMapper mapper = new ObjectMapper();
            String user = mapper.writeValueAsString(systemUser);
            map.put("systemUser",user);
            return AxiosResult.success("登录成功",map);
        }
    }

    @PostMapping("/logout")
    @ResponseBody
    public AxiosResult logout(){
        return AxiosResult.success("注销成功");
    }

}
