package com.djtu.settings.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

    /**
     * 后端默认主页提示
     *
     * @return 提示
     */
    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "this is SpringMVC running success";
    }

}
