package com.djtu.dorm.web.controller;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormApplyPageConditionVo;
import com.djtu.dorm.pojo.vo.DormApplyVo;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.exception.NothingException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.settings.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dorm")
public class DormController {

    @Autowired
    private DormService dormService;
    @Autowired
    private UserService userService;

    /**
     * 添加寝室接口
     *
     * @param dormVo 寝室vo
     * @return 添加成功
     * @throws DormException 添加失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/addDorm.do")
    @ResponseBody
    public Result addDorm(@RequestBody DormVo dormVo) throws DormException {
        dormService.addDormByDormVo(dormVo);
        return new Result().setCode(200).setMessage("添加寝室成功");
    }

    /**
     * 修改寝室接口
     *
     * @param dormVo 寝室vo
     * @return 修改成功
     * @throws DormException 修改失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/editDorm.do")
    @ResponseBody
    public Result editDorm(@RequestBody DormVo dormVo) throws DormException {
        dormService.editDormByDormVo(dormVo);
        return new Result().setCode(200).setMessage("修改寝室成功");
    }

    /**
     * 获取寝室列表
     *
     * @return 获取成功
     * @throws DormException 获取失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getDormList.do")
    @ResponseBody
    public Result getDormList() throws NothingException {
        List<Dorm> dormList = dormService.getDormList();
        return new Result().setCode(200).setMessage("获取寝室列表成功").setData(dormList);
    }

    /**
     * 删除寝室
     *
     * @return 删除成功
     * @throws DormException 删除失败
     * @map 寝室id
     */
    @RequiresRoles("管理员")
    @RequestMapping("/deleteDorm.do")
    @ResponseBody
    public Result deleteDorm(@RequestBody Map map) throws DormException {
        String id = (String) map.get("id");
        dormService.deleteDormByDormId(id);
        return new Result().setCode(200).setMessage("删除寝室成功");
    }

    /**
     * 获取楼宇和寝室的级联列表
     *
     * @return 列表数据
     */
    @RequiresRoles(value = {"管理员", "导员", "学生"}, logical = Logical.OR)
    @RequestMapping("/getDormOptions.do")
    @ResponseBody
    public Result getDormOptions() {
        List<Object> buildingDormList = dormService.getBuildingDormOptions();
        return new Result().setCode(200).setMessage("查询级联菜单成功").setData(buildingDormList);
    }

    /**
     * 根据用户id获取寝室信息
     *
     * @return 查询成功
     * @throws DormException 查询失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/getDormByUserId.do")
    @ResponseBody
    public Result getDormByUserId(String userId) throws NothingException {
        Dorm dorm = dormService.getDormByUserId(userId);
        return new Result().setCode(200).setMessage("查询寝室信息成功").setData(dorm);
    }

    /**
     * 根据用户id获取室友列表
     *
     * @return 查询成功
     * @throws DormException 查询失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/getDormFriendByUserId.do")
    @ResponseBody
    public Result getDormFriendByUserId(String userId) throws NothingException {
        List<Student> studentList = dormService.getDormFriendByUserId(userId);
        return new Result().setCode(200).setMessage("查询室友列表成功").setData(studentList);
    }

    /**
     * 换寝请求
     *
     * @return 请求成功
     * @throws DormException 请求失败
     */
    @RequiresRoles("学生")
    @RequestMapping("/changeDorm.do")
    @ResponseBody
    public Result changeDorm(@RequestBody Map map) throws DormException {
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String stuId = userService.getStudentIdByUserId(userId);
        String fromDorm = (String) map.get("fromDorm");
        String toDorm = (String) map.get("toDorm");
        dormService.addChangeDormApply(stuId, fromDorm, toDorm);
        return new Result().setCode(200).setMessage("寝室更换申请成功");
    }

    /**
     * 当前换寝状态
     *
     * @return 状态码
     */
    @RequiresRoles("学生")
    @RequestMapping("/getChangeStatus.do")
    @ResponseBody
    public Result getChangeStatus() throws NothingException {
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String stuId = userService.getStudentIdByUserId(userId);
        Integer status = dormService.getStatusByStuId(stuId);
        return new Result().setCode(200).setMessage("查询状态码成功").setData(status);
    }

    /**
     * 完成换寝
     *
     * @return 状态码
     */
    @RequiresRoles("学生")
    @RequestMapping("/finishChange.do")
    @ResponseBody
    public Result finishChange() throws DormException {
        String userId = ((UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo")).getUserId();
        String stuId = userService.getStudentIdByUserId(userId);
        dormService.finishChangeByStuId(stuId);
        return new Result().setCode(200).setMessage("成功完成换寝");
    }

    /**
     * 获取换寝申请列表
     * @param vo 用户id加分页数据
     * @return 换寝申请列表
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getDormChangeApplyList.do")
    @ResponseBody
    public Result getDormChangeApplyList(@RequestBody DormApplyPageConditionVo vo) throws NothingException {
        vo.setUserId(userService.getTutorIdByUserId(vo.getUserId()));
        List<DormApplyVo> dormApplyVoList = dormService.getDormChangeApplyList(vo);
        return new Result().setCode(200).setMessage("查询换寝申请列表成功").setData(dormApplyVoList);
    }

    /**
     * 获取换寝申请数据总量
     * @param vo userId
     * @return 换寝申请数据总量
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getDormChangeApplyTotal.do")
    @ResponseBody
    public Result getDormChangeApplyTotal(@RequestBody DormApplyPageConditionVo vo) {
        vo.setUserId(userService.getTutorIdByUserId(vo.getUserId()));
        int total = dormService.getDormChangeApplyTotal(vo);
        return new Result().setCode(200).setMessage("查询换寝申请列表成功").setData(total);
    }

    /**
     * 修改学生的换寝申请状态
     *
     * @param id 换寝申请表id
     * @param status 要更改的状态
     * @return 成功提示
     */
    @RequiresRoles(value = {"导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/setDormChangeApplyStatus.do")
    @ResponseBody
    public Result passDormChangeApply(String id, String status) throws DormException {
        dormService.setDormStatusById(id, status);
        return new Result().setCode(200).setMessage("修改换寝申请状态成功");
    }

}
