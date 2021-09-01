package com.hxy.yeb.service.impl;

import com.hxy.yeb.common.utils.TreeUtil;
import com.hxy.yeb.domain.Menu;
import com.hxy.yeb.mapper.MenuMapper;
import com.hxy.yeb.service.MenuService;
import com.hxy.yeb.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> searchMenusByUser(SysUser user, Long parentId) {
        List<Menu> menus = new ArrayList<>();
        if (user != null){
            menus = menuMapper.searchMenusByUser(user);
            menus = TreeUtil.menuListToTree(menus,parentId);
        }
        return menus;
    }

    @Override
    public Set<String> selectPermsByUserId(Long userId) {
        List<String> menus = menuMapper.selectPermsByUserId(userId);
        Set<String> menuSet = new HashSet<>();
        menus.forEach(menu -> {
            menuSet.add(menu);
        });
        return menuSet;
    }


}
