package com.exm.demo.yeb.menu.mapper;

import com.exm.demo.yeb.menu.domain.Menu;
import com.exm.demo.yeb.sysuser.domain.SystemUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    /**
     *  通过用户查找菜单
     *  @param user 用户
     *  @return
     */
    public List<Menu> searchMenusByUser(SystemUser user);
}
