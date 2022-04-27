package com.djtu.settings.web.controller;

import com.djtu.response.Result;
import com.djtu.settings.pojo.*;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.settings.pojo.vo.TutorRoleVo;
import com.djtu.settings.pojo.vo.TutorSearchVo;
import com.djtu.settings.service.*;
import com.djtu.settings.pojo.vo.StudentRoleVo;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
    @Autowired
    private StudentService studentService;

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
    public Result getUserPermissionList(@RequestBody Map map) {
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        String name = (String) map.get("name");
        String sno = (String) map.get("sno");
        String college = (String) map.get("college");
        String stuClass = (String) map.get("stuClass");
        List<String> date = (List) map.get("date");
        String startDate = null;
        String endDate = null;
        if (date != null && !date.isEmpty()) {
            startDate = date.get(0).substring(0, 10);
            endDate = date.get(1).substring(0, 10);
        }
        StudentSearchVo studentSearchVo = new StudentSearchVo(name, sno, college, stuClass, startDate, endDate);

        List<StudentRoleVo> studentRoleVoList = userService.getStudentUserRoleVoList(studentSearchVo, pageNo, pageSize);

        if (studentRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取学生角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生角色列表成功").setData(studentRoleVoList);
    }

    /**
     * 获取所有导员以及相对应的角色列表
     * @return 所有导员以及相对应的角色列表
     */
    @RequestMapping("/getTutorRoleList.do")
    @ResponseBody
    public Result getTutorRoleList(@RequestBody Map map) {
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        String name = (String) map.get("name");
        String college = (String) map.get("college");
        TutorSearchVo tutorSearchVo = new TutorSearchVo(username, name, college);

        List<TutorRoleVo> studentRoleVoList = userService.getTutorUserRoleVoList(tutorSearchVo, pageNo, pageSize);

        if (studentRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取导员角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取导员角色列表成功").setData(studentRoleVoList);
    }

    /**
     * 获取所有学生列表的总数
     * @return 所有学生列表的总数
     */
    @RequestMapping("/getStudentRoleListTotal.do")
    @ResponseBody
    public Result getStudentRoleListTotal(@RequestBody Map map) {
        String name = (String) map.get("name");
        String sno = (String) map.get("sno");
        String college = (String) map.get("college");
        String stuClass = (String) map.get("stuClass");
        List<String> date = (List) map.get("date");
        String startDate = null;
        String endDate = null;
        if (date != null && !date.isEmpty()) {
            startDate = date.get(0).substring(0, 10);
            endDate = date.get(1).substring(0, 10);
        }
        StudentSearchVo studentSearchVo = new StudentSearchVo(name, sno, college, stuClass, startDate, endDate);

        Integer total = userService.getStudentRoleListTotal(studentSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取学生列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表总数成功").setData(total);
    }

    /**
     * 获取所有导员列表的总数
     * @return 所有导员列表的总数
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getTutorRoleListTotal.do")
    @ResponseBody
    public Result getTutorRoleListTotal(@RequestBody Map map) {
        String username = (String) map.get("username");
        String name = (String) map.get("name");
        String college = (String) map.get("college");
        TutorSearchVo tutorSearchVo = new TutorSearchVo(username, name, college);

        Integer total = userService.getTutorRoleListTotal(tutorSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取导员列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取导员列表总数成功").setData(total);
    }

    /**
     * 根据前端传来的userId更改用户的对应角色列表
     * @return 是否更改成功
     */
    @RequestMapping("/changeUserRoleList.do")
    @ResponseBody
    public Result changeStudentRole(@RequestBody Map map){
        String userId = (String) map.get("id");
        List<String> roles = (List<String>) map.get("perms");
        List<Role> roleList = roleService.getRoleListByUserId(userId);

        Map<String, Integer> roleMap = new HashMap<>();
        roleMap.put("学生",1);
        roleMap.put("导员",2);
        roleMap.put("管理员",3);

        for (Role role : roleList) {
            boolean flag = true;
            for (String r : roles) {
                if (r.equals(role.getName())){
                    flag = false;
                }
            }
            if (flag != false) {
                roleService.removeUserRole(userId, roleMap.get(role.getName()));
            }
        }

        for (String r : roles) {
            boolean flag = true;
            for (Role role : roleList) {
                if (r.equals(role.getName())) {
                    flag = false;
                }
            }
            if (flag != false){
                roleService.addUserRole(userId, roleMap.get(r), StringUtil.generateUUID());
            }
        }
        return new Result().setCode(200).setMessage("修改权限成功");
    }

}
