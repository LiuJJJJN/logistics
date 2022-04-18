package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.User;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test1.do")
    @ResponseBody
    public String test1(){
        return "hello vue this is springMVC";
    }

    @RequestMapping("/test2.do")
    @ResponseBody
    public String test2(){
        return "hello2 vue this is springMVC";
    }

    @RequestMapping("/login.do")
    @ResponseBody
    public Result testLogin(@RequestBody User user) throws JsonProcessingException {
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

        return new Result().setCode(200).setMessage("登录成功");
    }

}
