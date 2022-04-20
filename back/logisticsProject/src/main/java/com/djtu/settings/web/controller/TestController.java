package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.Users;
import com.djtu.settings.service.UserService;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test1.do")
    @ResponseBody
    public String test1(){
       /* List<Users> list= userService.getAllUsers();
        System.out.println(list);*/
        return "hello vue this is springMVC";
    }

//    @RequiresPermissions("course:choose") //标注必须有"xxx"权限才能访问
    @RequiresRoles("stu2") //标注必须是"xxx"才能访问
    @RequestMapping("/test2.do")
    @ResponseBody
    public String test2(){
        System.out.println(SecurityUtils.getSubject().hasRole("stu"));
        return "hello2 vue this is springMVC";
    }

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result testLogin(@RequestBody Map<String, Object> reqMap) {
        User user = new User();
        user.setUsername((String) reqMap.get("username"));
        user.setPassword((String) reqMap.get("password"));
        System.out.println(user);
        Boolean rememberMe = (Boolean) reqMap.get("rememberMe");
        System.out.println(rememberMe);

        if (user.getUsername() == null || user.getPassword() == null){
            return new Result().setCode(500).setMessage("运行错误,用户名密码为空");
        }
        Subject subject = SecurityUtils.getSubject();
        //                                  用户名                问题             主体         过期时间 30分钟
        String jwt = JwtUtil.createJWT(user.getUsername(), "back", "user", 1000*60*30);
        JwtToken jwtToken = new JwtToken(jwt, user.getPassword());
        jwtToken.setRememberMe(rememberMe);
        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            return new Result().setCode(401).setMessage("账号不存在");
        } catch (IncorrectCredentialsException e) {
            return new Result().setCode(401).setMessage("密码错误");
        }

        User backUser = userService.getUserByUsername("mike");
        //将隐私信息设为空
        backUser.setPassword(null);
        backUser.setSalt(null);
        //配置返回 data 内容
        Map<String, Object> map = new HashMap<>();
        map.put("user", backUser);
        map.put("token", jwt);

        return new Result().setCode(200).setMessage("登录成功").setData(map);
    }

}
