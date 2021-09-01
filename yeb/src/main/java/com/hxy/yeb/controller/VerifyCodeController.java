package com.hxy.yeb.controller;

import com.hxy.yeb.common.utils.RedisUtil;
import com.hxy.yeb.domain.VerifyCode;
import com.hxy.yeb.service.IVerifyCodeGen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class VerifyCodeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    IVerifyCodeGen iVerifyCodeGen;

    @Autowired
    RedisUtil redisUtil;

    /**
     * 生成验证码
     * @param request
     * @param response
     */
    @GetMapping("/captcha")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) {
        try {
            //设置长宽
            VerifyCode verifyCode = iVerifyCodeGen.generate(80, 28);
            String code = verifyCode.getCode();
            LOGGER.info(code);
            //将VerifyCode绑定session
            request.getSession().setAttribute("VerifyCode", code);
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");
            response.getOutputStream().write(verifyCode.getImgBytes());
            response.getOutputStream().flush();
            redisUtil.set(request.getRemoteAddr()+"_verifyCode",code,60);
        } catch (IOException e) {
            LOGGER.info("", e);
        }
    }

}
