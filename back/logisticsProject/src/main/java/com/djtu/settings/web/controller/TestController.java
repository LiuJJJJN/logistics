package com.djtu.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping("/test.do")
    @ResponseBody
    public String test(){
        return "hello vue this is springMVC";
    }
}
