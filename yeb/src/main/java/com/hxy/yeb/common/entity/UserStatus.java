package com.hxy.yeb.common.entity;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
* @description 用户状态
* @date 2021/8/30 3:49 下午
* @author hanhf
*/
public enum UserStatus {
    OK(0, "正常"),
    DISABLE(1, "停用"),
    DELETED(2, "删除"),
    LOCKED(3, "锁定");

    private final Integer value;
    private final String name;

    UserStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    /**
     * @return
     * @Description 根据标识获取对应定义
     * @Param [value]
     */
    public static String getName(Integer value) {
        UserStatus[] userStatuses = values();
        for (UserStatus userStatus : userStatuses) {
            if (userStatus.getValue().equals(value)) {
                return userStatus.getName();
            }
        }
        return null;
    }

    /**
     * @return
     * @Description 根据标识获取枚举
     * @Param [value]
     */
    public static UserStatus getStatus(Integer value) {
        UserStatus[] userStatuses = values();
        for (UserStatus userStatus : userStatuses) {
            if (userStatus.getValue().equals(value)) {
                return userStatus;
            }
        }
        return null;
    }

    /**
     * @return
     * @Description 根据定义获取对应标识
     * @Param [name]
     */
    public static Integer getValue(String name) {
        UserStatus[] userStatuses = values();
        for (UserStatus userStatus : userStatuses) {
            if (userStatus.getName().equals(name)) {
                return userStatus.getValue();
            }
        }
        return null;
    }

    /**
     * @return
     * @Description 根据序号返回枚举常量名称，并进行组装
     * @Param [value]
     */
    public static String getConstant(Integer value) {
        String constant = "";
        UserStatus mainStatusEnum = getStatus(value);
        List<String> list = Arrays.asList(StringUtils.split(mainStatusEnum.toString(), "_"));
        // 切分后产生多个单词
        if (!CollectionUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);
                // 首字母大写，其他小写
                StringBuilder sb = new StringBuilder(str.toLowerCase());
                sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
                str = sb.toString();
                constant += str;
                if (i < list.size() - 1) {
                    constant += " ";
                }
            }
        }
        return constant;
    }
}
