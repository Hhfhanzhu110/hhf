package com.hxy.yeb.controller;

import com.hxy.yeb.common.entity.AxiosResult;
import com.hxy.yeb.domain.SysUser;
import com.hxy.yeb.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SystemUserService systemUserService;

    @RequestMapping("/register")
    public AxiosResult register(SysUser sysUser){
        int i = systemUserService.saveUser(sysUser);
        if (i==1){
            return AxiosResult.success();
        } else {
            return AxiosResult.error("添加失败");
        }
    }

}
