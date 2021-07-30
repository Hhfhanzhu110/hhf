package com.exm.demo.yeb.menu.controller;

import com.exm.demo.entity.AxiosResult;
import com.exm.demo.utils.UserContext;
import com.exm.demo.yeb.menu.domain.Menu;
import com.exm.demo.yeb.menu.service.MenuService;
import com.exm.demo.yeb.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/main/getMenusByUser")
    @ResponseBody
    public AxiosResult getMenusByUser(){
        User curreentUser = UserContext.getCurreentUser();
        if (curreentUser != null){
            List<Menu> menus = menuService.searchMenusByUser(curreentUser,1L);
            if (menus!=null){
                return AxiosResult.success(menus);
            } else {
                return AxiosResult.error();
            }
        } else {
            return AxiosResult.error(901,"请重新登录");
        }

    }
}
