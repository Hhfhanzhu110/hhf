package com.hxy.yeb.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hxy.yeb.common.entity.UserStatus;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class LoginUser extends SysUser implements UserDetails {

    private static final long serialVersionUID = -1379274258881257107L;

    /**
     * 用户权限
     */
    private List<Menu> menuPermissions;

    /**
     * token标识
     */
    private String token;

    /**
     * 登陆时间戳（毫秒）
     */
    private Long loginTime;

    /**
     * 过期时间戳
     */
    private Long expireTime;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return menuPermissions.parallelStream().filter(p -> !StringUtils.isEmpty(p.getPerms()))
                .map(p -> new SimpleGrantedAuthority(p.getPerms())).collect(Collectors.toSet());
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        // do nothing
    }

    // 账户是否未过期
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 账户是否未锁定
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return getStatus() != UserStatus.LOCKED.getValue();
    }

    // 密码是否未过期
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 账户是否激活
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

}
