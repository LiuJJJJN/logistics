package com.djtu.shiro.matchers;

import com.djtu.token.JwtToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

public class MyHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        //如果没有 token , 那就是第一次访问或者访问的是登陆页面, 直接放行
        if (((JwtToken) token).getPassword() == null){
            return true;
        }
        //如果有 token 那就是有身份, 进行验证 md5 的密码...
        return super.doCredentialsMatch(token, info);
    }

}
