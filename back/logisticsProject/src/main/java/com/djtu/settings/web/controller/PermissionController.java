package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.Permission;
import com.djtu.settings.pojo.Role;
import com.djtu.settings.service.PermissionService;
import com.djtu.settings.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/getPermissionList.do")
    @ResponseBody
    public Result getPermissionList(@RequestBody Map<String, String> map){
        String userId = map.get("userId");
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        List<Object> permList = permissionService.getPermissionListByRoleList(roleList);
        if (permList.isEmpty()) {
            return new Result().setCode(500).setMessage("获取权限列表失败");
        }
        return new Result().setCode(200).setMessage("获取权限列表成功").setData(permList);
    }

}
