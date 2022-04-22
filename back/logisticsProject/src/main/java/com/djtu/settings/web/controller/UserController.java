package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.djtu.settings.vo.UserStuVo;
import com.djtu.settings.vo.Vot;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login.do")
    @ResponseBody
    public Result login(@RequestBody Map<String, Object> map) {
        User user = new User();
        user.setUsername((String) map.get("username"));
        user.setPassword((String) map.get("password"));
        Boolean rememberMe = (Boolean) map.get("rememberMe");

        if (user.getUsername() == null || user.getPassword() == null){
            return new Result().setCode(500).setMessage("运行错误,用户名密码为空");
        }
        Subject subject = SecurityUtils.getSubject();
        //                                  用户名                问题             主体         过期时间 30分钟
        String jwt = JwtUtil.createJWT(user.getUsername(), "back", "user", 1000*60*30);
        JwtToken jwtToken = new JwtToken(jwt, user.getPassword());

        try {
            subject.login(jwtToken);
        } catch (UnknownAccountException e) {
            return new Result().setCode(401).setMessage("账号不存在");
        } catch (IncorrectCredentialsException e) {
            return new Result().setCode(401).setMessage("密码错误");
        }

        User backUser = userService.getUserByUsername(user.getUsername());
        //将隐私信息设为空
        backUser.setPassword(null);
        backUser.setSalt(null);
        //配置返回 data 内容
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("user", backUser); //传递用户信息
        resultMap.put("token", jwt); //传递token
        long timeStamp = System.currentTimeMillis()+1000*60*60*24*7; //设置过期时间为 7 天
        if (!rememberMe) {
            timeStamp = System.currentTimeMillis()+1000*60*30; //如果没选七天免登录的话, 过期时间为 30 分钟
        }
        resultMap.put("rememberMe", rememberMe); //传递是否七天免登录的标记
        resultMap.put("timestamp", timeStamp); //传递过期时间戳

        return new Result().setCode(200).setMessage("登录成功").setData(resultMap);
    }

    @RequestMapping("/registerStudent.do")
    @ResponseBody
    public Result registerStudent(@RequestBody UserStuVo vo){
        System.out.println(vo);
        return new Result();
    }

}
