package com.djtu.settings.web.controller;

import com.djtu.exception.RegisterException;
import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class TestController {

    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    public String none(){
        return "see vue this is springMVC";
    }

    @RequestMapping("/test1.do")
    @ResponseBody
    public String test1(@RequestBody Student student) throws RegisterException {
        //随机产生-盐
        String salt = StringUtil.rand4Str();
        //uuid
        student.setId(StringUtil.generateUUID());
        //密码通过盐与md5加密
        student.setPassword(StringUtil.md5(student.getPassword(),salt));
        userService.registerStudent(student);
        return "hello vue this is springMVC";
    }

//    @RequiresPermissions("course:choose") //标注必须有"xxx"权限才能访问
    @RequiresRoles("stu2") //标注必须是"xxx"才能访问
    @RequestMapping("/test2.do")
    @ResponseBody
    public String test2() {
        System.out.println(SecurityUtils.getSubject().hasRole("stu"));
        return "hello2 vue this is springMVC";
    }

    @RequestMapping(value = "/test3.do")
    @ResponseBody
    public String test3(String name){
        System.out.println(name);
        return "hello2 vue this is springMVC";
    }



}
