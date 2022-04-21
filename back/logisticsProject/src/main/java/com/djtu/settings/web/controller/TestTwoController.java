package com.djtu.settings.web.controller;

import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.sun.org.glassfish.gmbal.ParameterNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * littleHan测试用例
 */
@Controller
@RequestMapping("/data")
public class TestTwoController {
    @Autowired
    private UserService userService;

    @RequestMapping("/test.do")
    public void test1(@RequestParam(value="name") String name){
        System.out.println(name);
        User user=userService.userLogin(name);
        System.out.println(user);
    }
}
