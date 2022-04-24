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

    @RequestMapping("/addPerm.do")
    public void addPerm(){
        List<Permission> list = new ArrayList<>();
        list.add(new Permission("1","dorm", "寝室相关", "0", "/user", "1", "1"));
        list.add(new Permission("2","dorm:select", "我的寝室", "1", "/user/Dorm", "1", "1"));
        list.add(new Permission("3","dorm:update:power", "寝室缴纳电费", "1", "/user/powerDorm", "1", "1"));
        list.add(new Permission("4","dorm:update:water", "寝室缴纳水费", "1", "/user/waterDorm", "1", "1"));
        list.add(new Permission("5","dorm:change", "我要换寝", "1", "/user/changeDorm", "1", "1"));
        list.add(new Permission("6","classroom", "教室相关", "0", "/user", "1", "1"));
        list.add(new Permission("7","classroom:insert", "教室占座", "6", "/user/grabClassroom", "1", "1"));
        list.add(new Permission("8","library", "图书馆相关", "0", "/user", "1", "1"));
        list.add(new Permission("9","library:insert", "图书馆占座", "8", "/user/grabLibrary", "1", "1"));
        list.add(new Permission("10","feedback", "反馈相关", "0", "/user", "1", "1"));
        list.add(new Permission("11","feedback:insert", "我要反馈", "10", "/user/feedback", "1", "1"));
        list.add(new Permission("12","feedback:select", "我的反馈", "10", "/user/myFeedback", "1", "1"));
        list.add(new Permission("13","class", "班级相关", "0", "/tutor", "1", "2"));
        list.add(new Permission("14","class:*", "管理班级", "13", "/tutor/class", "1", "2"));
        list.add(new Permission("15","student", "学生相关", "0", "/tutor", "1", "2"));
        list.add(new Permission("16","student:*", "管理学生", "15", "/tutor/student", "1", "2"));
        list.add(new Permission("17","feedback", "反馈相关", "0", "/tutor", "1", "2"));
        list.add(new Permission("18","feedback:update", "学生反馈", "17", "/tutor/feedback", "1", "2"));
        list.add(new Permission("19","grab", "占座相关", "0", "/tutor", "1", "2"));
        list.add(new Permission("20","grab:*", "管理占座", "19", "/tutor/grab", "1", "2"));
        list.add(new Permission("21","user", "用户相关", "0", "/admin", "1", "3"));
        list.add(new Permission("22","user:*", "管理用户", "21", "/admin/user", "1", "3"));
        list.add(new Permission("23","classroom", "教室相关", "0", "/admin", "1", "3"));
        list.add(new Permission("24","classroom:*", "管理教室", "23", "/admin/classroom", "1", "3"));
        list.add(new Permission("25","library", "图书馆相关", "0", "/admin", "1", "3"));
        list.add(new Permission("26","library:*", "管理图书馆", "25", "/admin/library", "1", "3"));
        list.add(new Permission("27","permission", "权限相关", "0", "/admin", "1", "3"));
        list.add(new Permission("28","permission:*", "管理权限", "27", "/admin/permission", "1", "3"));
        list.add(new Permission("29","dict", "数据字典相关", "0", "/admin", "1", "3"));
        list.add(new Permission("30","dicType:*", "管理数据字典类型", "29", "/admin/dicType", "1", "3"));
        list.add(new Permission("31","dicValue:*", "管理数据字典值", "29", "/admin/dicValue", "1", "3"));
        for (Permission permission : list) {
            //permissionDao.addPermList(permission);
        }
    }

}
