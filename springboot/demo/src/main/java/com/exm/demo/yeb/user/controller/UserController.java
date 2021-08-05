package com.exm.demo.yeb.user.controller;

import com.exm.demo.yeb.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    SystemUserService systemUserService;

}
