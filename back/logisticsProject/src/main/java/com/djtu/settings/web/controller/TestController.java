package com.djtu.settings.web.controller;

import org.springframework.stereotype.Controller;
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
    public String testLogin(){
        return "little bitch has you login ? ";
    }
}
