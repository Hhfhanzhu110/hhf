package com.hxy.yeb.controller;

import com.hxy.yeb.common.entity.AxiosResult;
import com.hxy.yeb.common.utils.SecurityUtils;
import com.hxy.yeb.config.jwt.JwtTokenUtils;
import com.hxy.yeb.domain.Menu;
import com.hxy.yeb.service.MenuService;
import com.hxy.yeb.domain.SysUser;
import com.hxy.yeb.service.SystemUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/system/menu")
public class MenuController {

    @Autowired
    MenuService menuService;
    @Autowired
    JwtTokenUtils jwtTokenUtils;
    @Autowired
    SystemUserService systemUserService;

    @RequestMapping("/main/getMenusByUser")
    @ResponseBody
    public AxiosResult getMenusByUser(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(token)){
            SysUser sysUser = SecurityUtils.getCurrentUser();
            List<Menu> menus = menuService.searchMenusByUser(sysUser,1L);
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
