package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.djtu.token.JwtToken;
import com.djtu.utils.JwtUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test1.do")
    @ResponseBody
    public String test1(){
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

    @RequestMapping("/test.do")
    public void test1(@RequestParam(value="name") String name){
        System.out.println(name);
        User user=userService.userLogin(name);
        System.out.println(user);
    }

}
