package com.hxy.yeb.service;

import com.hxy.yeb.domain.Menu;
import com.hxy.yeb.domain.SysUser;

import java.util.List;
import java.util.Set;

public interface MenuService {

    /**
     * 通过用户查找菜单
     * @param user 用户
     * @return
     */
    public List<Menu> searchMenusByUser(SysUser user, Long parentId);

    public Set<String> selectPermsByUserId(Long userId);
}
