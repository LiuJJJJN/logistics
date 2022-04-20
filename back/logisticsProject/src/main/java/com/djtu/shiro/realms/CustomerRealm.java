package com.djtu.shiro.realms;

import com.djtu.settings.pojo.User;
import com.djtu.settings.service.PermissionService;
import com.djtu.settings.service.RoleService;
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

import java.util.Set;

@Component
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    //验证是否使用的是自定义 token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String username = (String) principal.iterator().next();
        Set<String> roles = roleService.getRoleByUsername(username);
        Set<String> permissions = permissionService.getPermissionByUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
        info.addStringPermissions(permissions);
        return info;
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
