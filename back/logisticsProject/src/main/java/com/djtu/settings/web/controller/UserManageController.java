package com.djtu.settings.web.controller;

import com.djtu.exception.UserManagerException;
import com.djtu.response.Result;
import com.djtu.dictionary.pojo.DicValue;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.settings.pojo.vo.TutorVo;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.settings.service.StudentService;
import com.djtu.settings.service.UserManageService;
import com.djtu.settings.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * 管理学生、导员相关接口
 */

@Controller
public class UserManageController {

    @Autowired
    private UserManageService userManageService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    /**
     * 获取导员信息
     *
     * @param tutorVo 导员实例
     * @return 导员信息列表
     * @throws UserManagerException 查询失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/getTutorList.do")
    @ResponseBody
    public Result getTutorList(@RequestBody TutorVo tutorVo) throws UserManagerException {
        List<Tutor> list = userManageService.getTutorList(tutorVo);
        Integer num = userManageService.gitTutorNum();
        Map<String, Object> map = new Hashtable<>();
        map.put("total", num);
        map.put("list", list);
        return new Result().setCode(200).setMessage("查询成功").setData(map);
    }

    /**
     * 获取college列表
     *
     * @return 学院列表
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/getCollegeList.do")
    @ResponseBody
    public Result getCollegeList() {
        List<DicValue> list = userManageService.getCollegeList();
        return new Result().setCode(200).setMessage("查询成功").setData(list);
    }

    /**
     * 批量重置导员密码
     *
     * @param data 管理员id列表
     * @return 重置管理员密码成功
     * @throws UserManagerException 重置失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/resetTP.do")
    @ResponseBody
    public Result resetTutorPwd(@RequestBody List<String> data) throws UserManagerException {
        userManageService.resetTutorPwd(data);
        return new Result().setCode(200).setMessage("重置成功");
    }

    /**
     * 批量删除导员
     *
     * @param data 导员id列表
     * @return 是否删除信息
     * @throws UserManagerException 删除失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/delTutorL.do")
    @ResponseBody
    public Result delTutorList(@RequestBody List<String> data) throws UserManagerException {
        userManageService.delTutorList(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 添加或修改导员备注
     *
     * @param tutor 导员实例
     * @return 是否添加/修改信息
     * @throws UserManagerException 修改备注失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/admin/manage/addOrUpTutorRemark.do")
    @ResponseBody
    public Result addOrUpTutorRemark(@RequestBody Tutor tutor) throws UserManagerException {
        userManageService.addOrUpTutorRemark(tutor);
        return new Result().setCode(200).setMessage("添加/修改成功");
    }

    /**
     * 分页、模糊查询学生列表
     *
     * @param map 分页、查询信息
     * @return 学生列表
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/admin/manage/getStudentList.do")
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

        List<Student> studentList = userManageService.getStudentList(studentSearchVo, pageNo, pageSize);

        if (studentList == null) {
            return new Result().setCode(402).setMessage("获取学生列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表成功").setData(studentList);
    }

    /**
     * 分页、模糊查询学生列表
     *
     * @param map 分页、查询信息
     * @return 学生列表
     */
    @RequiresRoles("导员")
    @RequestMapping("/admin/manage/getStudentListByTutor.do")
    @ResponseBody
    public Result getStudentListByTutor(@RequestBody Map map) {
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
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

        List<Student> studentList = userManageService.getStudentList(tutorId, studentSearchVo, pageNo, pageSize);

        if (studentList == null) {
            return new Result().setCode(402).setMessage("获取学生列表失败");
        }
        return new Result().setCode(200).setMessage("获取学生列表成功").setData(studentList);
    }

    /**
     * 修改学生备注
     *
     * @param map 学生id及新备注
     * @return 修改成功提示
     * @throws UserManagerException 修改失败错误提示
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/admin/manage/editStudentRemark.do")
    @ResponseBody
    public Result editStudentRemark(@RequestBody Map map) throws UserManagerException {
        String id = (String) map.get("id");
        String remark = (String) map.get("remark");
        studentService.editStudentRemarkById(id, remark);

        return new Result().setCode(200).setMessage("修改学生备注成功");
    }

    /**
     * 修改学生寝室
     *
     * @param map 学生id及寝室id
     * @return 修改成功提示
     * @throws UserManagerException 修改失败错误提示
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/admin/manage/editStudentDorm.do")
    @ResponseBody
    public Result editStudentDorm(@RequestBody Map map) throws UserManagerException {
        String id = (String) map.get("id");
        String dormId = (String) map.get("dormId");
        studentService.editStudentDormById(id, dormId);

        return new Result().setCode(200).setMessage("修改学生寝室成功");
    }

    /**
     * 批量删除学生
     *
     * @param data 学生id列表
     * @return 是否删除信息
     * @throws UserManagerException 删除失败抛出异常提示
     */
    @RequiresRoles(value = {"管理员", "导员"}, logical = Logical.OR)
    @RequestMapping("/admin/manage/delStudentL.do")
    @ResponseBody
    public Result delStudentList(@RequestBody List<String> data) throws UserManagerException {
        userManageService.delStudentList(data);
        return new Result().setCode(200).setMessage("删除成功");
    }

    /**
     * 重置学生密码
     *
     * @param map 学生id
     * @return 修改成功提示
     * @throws UserManagerException 修改失败抛出异常提示
     */
    @RequiresRoles(value = {"管理员", "导员"}, logical = Logical.OR)
    @RequestMapping("/admin/manage/resetStudentPassword.do")
    @ResponseBody
    public Result resetStudentPassword(@RequestBody Map map) throws UserManagerException {
        String id = (String) map.get("id");
        userManageService.resetStudentPwd(id);
        return new Result().setCode(200).setMessage("修改成功");
    }

    /**
     * 导员认领学生: 修改学生的导员外键
     *
     * @param map 导员id 学生id
     * @return 是否认领成功
     */
    @RequiresRoles("导员")
    @RequestMapping("/tutor/claimStu.do")
    @ResponseBody
    public Result claimStu(@RequestBody Map map) throws UserManagerException {
        String stuId = (String) map.get("stuId");
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String tutorId = userService.getTutorIdByUserId(userId);
        userManageService.editStudentTutorIdById(stuId, tutorId);
        return new Result().setCode(200).setMessage("认领学生成功");
    }

    /**
     * 导员抛弃学生: 修改学生的导员外键
     *
     * @param stuId 学生id
     * @return 是否认领成功
     */
    @RequiresRoles("导员")
    @RequestMapping("/tutor/abandonStu.do")
    @ResponseBody
    public Result abandonStu(String stuId) throws UserManagerException {
        userManageService.delStudentTutorIdByStudentId(stuId);
        return new Result().setCode(200).setMessage("抛弃学生成功");
    }

}
