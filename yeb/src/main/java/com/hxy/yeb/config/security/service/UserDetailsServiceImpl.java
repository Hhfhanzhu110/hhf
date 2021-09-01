package com.hxy.yeb.config.security.service;

import com.hxy.yeb.common.entity.UserStatus;
import com.hxy.yeb.domain.LoginUser;
import com.hxy.yeb.domain.Menu;
import com.hxy.yeb.domain.Role;
import com.hxy.yeb.domain.SysUser;
import com.hxy.yeb.mapper.MenuMapper;
import com.hxy.yeb.mapper.RoleMapper;
import com.hxy.yeb.mapper.SystemUserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SpringSecurity 自定义登录逻辑
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SystemUserMapper systemUserMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    MenuMapper menuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = systemUserMapper.queryUserByUsername(username);
        if (sysUser == null){
            throw new AuthenticationCredentialsNotFoundException("用户名不存在");
        } else if (sysUser.getStatus() == UserStatus.LOCKED.getValue()){
            throw new LockedException("用户被锁定,请联系管理员");
        } else if(sysUser.getStatus() == UserStatus.DISABLE.getValue()) {
            throw new DisabledException("用户已作废");
        }
        List<Role> roles = roleMapper.selectRoleKeys(sysUser.getUserId());
        List<Menu> menus = menuMapper.searchMenusByUser(sysUser);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser,loginUser);
        loginUser.setRoles(roles);
        loginUser.setMenuPermissions(menus);
        return loginUser;
    }
}
