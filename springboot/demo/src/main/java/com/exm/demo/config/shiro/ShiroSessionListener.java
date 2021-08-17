package com.exm.demo.config.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
/**
* @description shiro会话监听器
* @date 2021/8/17 11:12 上午
* @author hanhf
*/
public class ShiroSessionListener implements SessionListener {


    @Override
    public void onStart(Session session) {
        System.out.println("会话开始："+session.getId());
    }

    @Override
    public void onStop(Session session) {
        System.out.println("会话结束：" + session.getId());
    }

    @Override
    public void onExpiration(Session session) {
        System.out.println("会话过期：" + session.getId());
    }
}
