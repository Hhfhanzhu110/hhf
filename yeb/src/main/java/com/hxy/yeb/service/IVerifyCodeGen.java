package com.hxy.yeb.service;


import com.hxy.yeb.domain.VerifyCode;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 验证码生成
 */
public interface IVerifyCodeGen {
    /**
     * 生成验证码并返回code，将图片写的os中
     *
     * @param width
     * @param height
     * @param os
     * @return
     * @throws IOException
     */
    public String generate(int width, int height, OutputStream os) throws IOException;

    /**
     * 生成验证码对象
     *
     * @param width
     * @param height
     * @return
     * @throws IOException
     */
    VerifyCode generate(int width, int height) throws IOException;
}