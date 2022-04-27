package com.djtu.shiro.realms;

import com.djtu.settings.service.*;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
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

import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AdminService adminService;
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
//        Set<String> roles = roleService.getRoleByUsername(username);
//        Set<String> permissions = permissionService.getPermissionByUsername(username);

        Set<String> roles = new HashSet<>();
        roles.add("导员");

        System.out.println("============================================");
        System.out.println(username);
        System.out.println(SecurityUtils.getSubject());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRoles(roles);
//        info.addStringPermissions(null);
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
        String ident = claims.getSubject();

        UserVo userVo = null;
        if ("学生".equals(ident)) {
            userVo = userService.getUserVoByStudentUsername(username);
        }else if ("导员".equals(ident)) {
            userVo = userService.getUserVoByTutorUsername(username);
        }else if ("管理员".equals(ident)) {
            userVo = userService.getUserVoByAdminUsername(username);
        }

        if (userVo == null){
            return null;
        }
        return new SimpleAuthenticationInfo(
                username, //用户名
                userVo.getPassword(), //密码
                ByteSource.Util.bytes(userVo.getSalt()), //盐
                this.getName());
    }

}
