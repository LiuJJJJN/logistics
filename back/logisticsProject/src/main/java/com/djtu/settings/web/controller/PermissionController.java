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
    private UserService userService;

    /**
     * 根据userId获取当前角色的所有功能列表
     * @param map 前端发过来的 suerId
     * @return 功能列表
     */
    @RequestMapping("/getPermissionList.do")
    @ResponseBody
    public Result getPermissionList(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        List<Object> permList = permissionService.getPermissionListByRoleList(roleList);
        if (permList.isEmpty()) {
            return new Result().setCode(500).setMessage("获取权限列表失败");
        }
        return new Result().setCode(200).setMessage("获取权限列表成功").setData(permList);
    }

    /**
     * 获取所有学生以及相对应的角色列表
     * @return 所有学生以及相对应的角色列表
     */
    @RequestMapping("/getStudentRoleList.do")
    @ResponseBody
    public Result getUserPermissionList() {

        List<UserRoleVo> userRoleVoList = userService.getStudentUserRoleVoList(1, 3);

        if (userRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取学生角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生角色列表成功").setData(userRoleVoList);
    }

}
