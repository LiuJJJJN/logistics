package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;


    @RequestMapping("/getPermissionList")
    @ResponseBody
    public Result getPermissionList(String userId){
        permissionService.get
        return null;
    }

}
