package com.exm.demo.yeb.menu.service;

import com.exm.demo.yeb.menu.domain.Menu;
import com.exm.demo.yeb.user.domain.User;

import java.util.List;

public interface MenuService {

    /**
     * 通过用户查找菜单
     * @param user 用户
     * @return
     */
    public List<Menu> searchMenusByUser(User user, Long parentId);
}
