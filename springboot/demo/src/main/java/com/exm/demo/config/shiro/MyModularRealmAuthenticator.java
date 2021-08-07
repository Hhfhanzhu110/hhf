package com.exm.demo.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

//自定义认证器，处理多个realms 时执行指定realm
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        this.assertRealmsConfigured();
        Collection<Realm> realms = this.getRealms();
        MyToken token = (MyToken) authenticationToken;
        String loginType = token.getLoginType();
        Collection<Realm> myRealms = new ArrayList<>();
        realms.forEach(realm -> {
            if(realm.getName().startsWith(loginType)){
                myRealms.add(realm);
            }
        });
        if (myRealms.size()==1){
            return this.doSingleRealmAuthentication((Realm)realms.iterator().next(), authenticationToken);
        } else {
            return this.doMultiRealmAuthentication(realms, authenticationToken);
        }
    }
}
