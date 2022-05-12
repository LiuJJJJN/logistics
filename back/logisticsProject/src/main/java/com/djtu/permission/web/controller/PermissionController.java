package com.djtu.permission.web.controller;

import com.djtu.exception.UploadException;
import com.djtu.permission.pojo.Role;
import com.djtu.permission.pojo.vo.StudentRoleVo;
import com.djtu.permission.pojo.vo.TutorRoleVo;
import com.djtu.permission.service.PermissionService;
import com.djtu.permission.service.RoleService;
import com.djtu.response.Result;
import com.djtu.settings.pojo.vo.*;
import com.djtu.settings.service.*;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
     *
     * @param map 前端发过来的 suerId
     * @return 功能列表
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getPermissionList.do")
    @ResponseBody
    public Result getPermissionList(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        List<Object> permList = permissionService.getPermissionListByRoleList(roleList);
        if (permList.isEmpty()) {
            return new Result().setCode(401).setMessage("获取权限列表失败, 请重新登录");
        }
        return new Result().setCode(200).setMessage("获取权限列表成功").setData(permList);
    }

    /**
     * 根据userId获取当前角色的所有功能列表, 静默无提示
     *
     * @param map 前端发过来的 suerId
     * @return 功能列表
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getPermissionWelcomeList.do")
    @ResponseBody
    public Result getPermissionWelcomeList(@RequestBody Map<String, String> map) {
        String userId = map.get("userId");
        List<Role> roleList = roleService.getRoleListByUserId(userId);
        List<Object> permList = permissionService.getPermissionListByRoleList(roleList);
        if (permList.isEmpty()) {
            return new Result().setCode(403).setMessage("获取权限列表失败, 请重新登录");
        }
        return new Result().setCode(200).setMessage("获取权限列表成功").setData(permList);
    }

    /**
     * 获取所有学生以及相对应的角色列表
     *
     * @return 所有学生以及相对应的角色列表
     */
    @RequiresRoles(value = {"管理员", "导员"}, logical = Logical.OR)
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

        List<StudentRoleVo> studentRoleVoList = roleService.getStudentUserRoleVoList(studentSearchVo, pageNo, pageSize, "");

        if (studentRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取学生角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生角色列表成功").setData(studentRoleVoList);
    }

    /**
     * 获取导员所管理的学生以及相对应的角色列表
     *
     * @return 所有学生以及相对应的角色列表
     */
    @RequiresRoles("导员")
    @RequestMapping("/getStudentRoleListByTutor.do")
    @ResponseBody
    public Result getStudentRoleListByTutor(@RequestBody Map map) {
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        String userId = userVo.getUserId();
        String tutorId = userService.getTutorIdByUserId(userId);
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

        List<StudentRoleVo> studentRoleVoList = roleService.getStudentUserRoleVoList(studentSearchVo, pageNo, pageSize, tutorId);

        if (studentRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取学生角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生角色列表成功").setData(studentRoleVoList);
    }

    /**
     * 获取所有导员以及相对应的角色列表
     *
     * @return 所有导员以及相对应的角色列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getTutorRoleList.do")
    @ResponseBody
    public Result getTutorRoleList(@RequestBody Map map) {
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        String name = (String) map.get("name");
        String college = (String) map.get("college");
        TutorSearchVo tutorSearchVo = new TutorSearchVo(username, name, college);

        List<TutorRoleVo> studentRoleVoList = roleService.getTutorUserRoleVoList(tutorSearchVo, pageNo, pageSize);

        if (studentRoleVoList == null) {
            return new Result().setCode(402).setMessage("获取导员角色列表失败");
        }
        return new Result().setCode(200).setMessage("获取导员角色列表成功").setData(studentRoleVoList);
    }

    /**
     * 获取所有学生列表的总数
     *
     * @return 所有学生列表的总数
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getStudentListTotal.do")
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

        Integer total = roleService.getStudentRoleListTotal(studentSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取学生列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表总数成功").setData(total);
    }

    /**
     * 根据导员id获取所有学生列表的总数
     *
     * @return 所有学生列表的总数
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getStudentListTotalByTutor.do")
    @ResponseBody
    public Result getStudentListTotalByTutor(@RequestBody Map map) {
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

        Integer total = roleService.getStudentRoleListTotal(studentSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取学生列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表总数成功").setData(total);
    }

    /**
     * 获取所有学生列表的总数
     *
     * @return 所有学生列表的总数
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getStudentListByTutorTotal.do")
    @ResponseBody
    public Result getStudentListByTutorTotal(@RequestBody Map map) {
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String tutorId = userService.getTutorIdByUserId(userId);
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

        Integer total = roleService.getStudentRoleListTotal(tutorId, studentSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取学生列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表总数成功").setData(total);
    }

    /**
     * 下载学生Excel
     *
     * @return 是否下载成功
     */
    //@RequiresRoles(value = {"导员"})
    @RequestMapping(value = "/downloadStu.do", method = RequestMethod.POST)
    //@ResponseBody
    public void downloadMyStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");
        studentService.downloadMyStudent(id, response);
    }

    /**
     * 上传学生信息
     *
     * @param file    前端传过来的文件
     * @param request 请求
     * @throws IOException
     */
    @RequestMapping(value = "/uploadMyStu.do", method = RequestMethod.POST)
    @ResponseBody
    public Result uploadMyStudent(MultipartFile file,HttpServletRequest request) throws IOException, UploadException {
        String userId=request.getParameter("id");
        studentService.uploadMyStudent(userId,file,request);
        return new Result().setCode(200).setMessage("上传成功");
    }

    /**
     * 下载模板
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/downloadM.do",method = RequestMethod.POST)
    public void downloadModel(HttpServletResponse response) throws IOException {
        studentService.downloadModel(response);
    }

    /**
     * 管理员下载学生Excel
     * @param response
     */
    @RequestMapping(value = "/adminDownLoadStu.do", method = RequestMethod.POST)
    //@ResponseBody
    public void adminDownLoadStudent(HttpServletResponse response){
        try {
            studentService.adminDownLoadStudent(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员上传文件（学生表）
     * @param file 文件对象
     */
    @RequestMapping(value = "/adminUpLoadStu.do", method = RequestMethod.POST)
    @ResponseBody
    public Result adminUpLoadStudent(MultipartFile file) throws UploadException{
        try {
            studentService.adminUpLoadStudent(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result().setCode(200).setMessage("上传成功");
    }

    /**
     * 管理员-下载模板
     * @param response 响应
     * @return
     */
    @RequestMapping(value = "/adminDownLoadM.do",method = RequestMethod.POST)
    public void adminDownLoadModel(HttpServletResponse response) throws IOException {
        studentService.adminDownLoadModel(response);
    }

    /**
     * 获取所有导员列表的总数
     *
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

        Integer total = roleService.getTutorRoleListTotal(tutorSearchVo);

        if (total == null) {
            return new Result().setCode(402).setMessage("获取导员列表总数失败");
        }
        return new Result().setCode(200).setMessage("获取导员列表总数成功").setData(total);
    }

    /**
     * 根据前端传来的userId更改用户的对应角色列表
     *
     * @return 是否更改成功
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/changeUserRoleList.do")
    @ResponseBody
    public Result changeStudentRole(@RequestBody Map map) {
        String userId = (String) map.get("id");
        List<String> roles = (List<String>) map.get("perms");
        List<Role> roleList = roleService.getRoleListByUserId(userId);

        Map<String, Integer> roleMap = new HashMap<>();
        roleMap.put("学生", 1);
        roleMap.put("导员", 2);
        roleMap.put("管理员", 3);

        for (Role role : roleList) {
            boolean flag = true;
            for (String r : roles) {
                if (r.equals(role.getName())) {
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
            if (flag != false) {
                roleService.addUserRole(userId, roleMap.get(r), StringUtil.generateUUID());
            }
        }
        return new Result().setCode(200).setMessage("修改权限成功");
    }

}
