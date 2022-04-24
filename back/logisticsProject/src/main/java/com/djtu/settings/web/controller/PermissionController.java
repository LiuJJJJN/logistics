package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.*;
import com.djtu.settings.service.*;
import com.djtu.settings.pojo.vo.UserRoleVo;
import com.djtu.settings.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TutorService tutorService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

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

    @RequestMapping("/getUserRoleList.do")
    @ResponseBody
    public Result getUserPermissionList(){

        List<UserRoleVo> userRoleVoList = userService.getStudentUserRoleVoList(1, 3);

        if (userRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取用户角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取用户角色列表成功").setData(userRoleVoList);
    }

}
