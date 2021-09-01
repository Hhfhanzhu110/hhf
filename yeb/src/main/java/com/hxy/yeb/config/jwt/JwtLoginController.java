package com.hxy.yeb.config.jwt;

import com.hxy.yeb.common.entity.AxiosResult;
import com.hxy.yeb.common.utils.RedisUtil;
import com.hxy.yeb.common.utils.SecurityUtils;
import com.hxy.yeb.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
public class JwtLoginController {
    private final String VERIFYCODE = "_verifyCode";
    @Autowired
    private JwtAuthService jwtAuthService;
    @Autowired
    private RedisUtil redisUtil;

    /**
    * @description 用户登录
    * @param request
    * @param username 用户名
    * @param password 密码
    * @param verifyCode 验证码
    * @date 2021/8/31 4:35 下午
    * @author hanhf
    * @return com.hxy.yeb.common.entity.AxiosResult
    */
    @PostMapping("/login")
    public AxiosResult login(HttpServletRequest request, String username, String password, String code) {
        String ip = request.getRemoteAddr();
        String verifyCode = (String) redisUtil.get(ip + VERIFYCODE);
        if (code != null){
            if (code.equalsIgnoreCase(verifyCode)){
                Map<String, Object> resMap = new HashMap<>();
                try {
                    String token = jwtAuthService.login(username, password);
                    SysUser sysUser = SecurityUtils.getCurrentUser();
                    resMap.put("token",token);
                    resMap.put("sysUser",sysUser);
                    return AxiosResult.success(resMap);
                }catch (Exception e){
                    e.printStackTrace();
                    return AxiosResult.error("用户名密码错误！！");
                }
            } else {
                return AxiosResult.error("验证码错误");
            }
        } else {
            return AxiosResult.error("验证码失效");
        }
    }


}
