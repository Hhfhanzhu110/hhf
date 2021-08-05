package com.exm.demo.config.shiro;

import com.exm.demo.yeb.Role.service.RoleService;
import com.exm.demo.yeb.menu.service.MenuService;
import com.exm.demo.yeb.user.domain.User;
import com.exm.demo.yeb.user.service.SystemUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    MenuService menuService;
    @Autowired
    RoleService roleService;
    @Autowired
    SystemUserService userService;

    /**
     * 获取授权安全数据
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //此处取值是什么取决于认证处第一参数存的啥
        User user = (User) principalCollection.iterator().next();
        Set<String> roles = new HashSet<>();
        Set<String> menus = new HashSet<>();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        roles = roleService.selectRoleKeys(user.getUserId());
        menus = menuService.selectPermsByUserId(user.getUserId());
        info.setRoles(roles);
        info.setStringPermissions(menus);
        return info;
    }

    /**
     * 获取认证安全数据
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.queryUserByUsername(username);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(), getName());//user.getUsername的存储决定了授权取值是啥
        return info;
    }

    @Override
    public String getName() {
        return "userRealm";
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
