package com.exm.demo.yeb.sysuser.controller;

import com.exm.demo.entity.AxiosResult;
import com.exm.demo.utils.UserContext;
import com.exm.demo.yeb.sysuser.domain.SystemUser;
import com.exm.demo.yeb.sysuser.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class SystemUserController {

    @Autowired
    SystemUserService systemUserService;

    /**
     * 登录控制
     */
    @PostMapping("/login")
    @ResponseBody
    public AxiosResult login(String username, String password,String code,HttpServletRequest request){
        //校验验证码
        String verifyCode = request.getSession().getAttribute("VerifyCode").toString();
        if (StringUtils.isBlank(verifyCode)){
            return AxiosResult.error("验证码错误过期");
        } else if (!code.equals(verifyCode)){
            return AxiosResult.error("验证码错误");
        }
        //校验用户名和密码
        SystemUser systemUser = systemUserService.login(username, password);
        if(systemUser==null){
            return AxiosResult.error("用户名或密码错误");
        }else{
            UserContext.putCurrebtUser(systemUser);
        }
        return AxiosResult.success();
    }
}
