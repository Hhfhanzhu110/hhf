package com.exm.demo.yeb.menu.service.impl;

import com.exm.demo.utils.TreeUtil;
import com.exm.demo.yeb.menu.domain.Menu;
import com.exm.demo.yeb.menu.mapper.MenuMapper;
import com.exm.demo.yeb.menu.service.MenuService;
import com.exm.demo.yeb.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> searchMenusByUser(User user, Long parentId) {
        List<Menu> menus = new ArrayList<>();
        if (user != null){
            menus = menuMapper.searchMenusByUser(user);
            menus = TreeUtil.menuListToTree(menus,parentId);
        }
        return menus;
    }


}
