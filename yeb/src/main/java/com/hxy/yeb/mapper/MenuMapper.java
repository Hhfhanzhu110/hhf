package com.hxy.yeb.mapper;

import com.hxy.yeb.domain.Menu;
import com.hxy.yeb.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {

    /**
     * 通过用户查找菜单
     *
     * @param user 用户
     * @return
     */
    public List<Menu> searchMenusByUser(SysUser user);

    List<String> selectPermsByUserId(Long userId);
}
