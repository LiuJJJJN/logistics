package com.djtu.shiro.realms;

import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class CustomerRealm extends AuthorizingRealm {

    /**
     * 这里为何能用 @Autowired ?
     * 因为这个类是由 Spring 容器管理的, 在 com.djtu.config.ShiroConfig 中配置
     */
    @Autowired
    private UserService userService;

    //验证是否使用的是自定义 token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {

        //向下转型 token 为自定义 token 类型
        JwtToken jwtToken = (JwtToken) token;
        //获取 token 实例中的 token 信息
        String jwt = (String) jwtToken.getPrincipal();
        //使用工具类解析 token
        Claims claims = JwtUtil.parseJWT(jwt);
        //获取解析后的用户名
        String username = claims.getId();
        System.out.println(username);

        User user = userService.getUserByUsername(username);
        if (user == null){
            return null;
        }
        return new SimpleAuthenticationInfo(
                username, //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getSalt()), //盐
                this.getName());
    }

}
