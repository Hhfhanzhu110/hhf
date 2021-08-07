package com.exm.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * md5+salt加密
 */
public class Md5SaltUtil {
    //摘要算法
    private static final String MD5_HASH = "MD5";

    //加密次数
    private static final int HASH_ITERATIONS = 1;

    /**
     * 密码加密，采用shiro框架里的SimpleHash算法
     * 通过MD5算法+salt来加密，加密三次
     *
     * @param pass 原始密码
     * @param salt 盐值
     * @return 加密后的摘要
     */
    public static String encoderPassword(String pass, String salt) {
        Object object = new SimpleHash(MD5_HASH, pass, salt, HASH_ITERATIONS);
        return object.toString();
    }
}
