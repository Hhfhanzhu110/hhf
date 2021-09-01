package com.hxy.yeb.domain;

import com.hxy.yeb.common.entity.BaseEntity;
import lombok.Data;

import java.util.List;

@Data
public class SysUser extends BaseEntity{
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 联系方式
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱地址
     */
    private String address;

    /**
     * 用户状态 0 启用，1禁用
     */
    private Integer status;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 登录名称
     */
    private String username;

    /**
     * 用户性别 0 男 1女
     */
    private String sex;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String realPass;

    /**
     * 部门对象
     */
    private Dept dept;

    /**
     * 角色集合
     */
    private List<Role> roles;

    /**
     * 角色组
     */
    private Long[] roleIds;

    /**
     * 行政区号
     */
    private Long xzqhCode;

    private Integer authorize;

    //联系人
    private String linkMan;

    /**
     * 盐加密
     */
    private String salt;

    public static boolean isRoot(SysUser user){
        return user!=null && user.getUserId()==1l;
    }
}
