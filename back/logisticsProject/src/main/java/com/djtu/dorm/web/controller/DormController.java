package com.djtu.dorm.web.controller;

import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.response.Result;
import com.djtu.settings.pojo.Student;
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
    public Result getDormList() throws DormException {
        List<Dorm> dormList =  dormService.getDormList();
        return new Result().setCode(200).setMessage("获取寝室列表成功").setData(dormList);
    }

    /**
     * 删除寝室
     *
     * @map 寝室id
     * @return 删除成功
     * @throws DormException 删除失败
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
     * @return 查询成功
     * @throws DormException 查询失败
     */
    @RequiresRoles(value = {"管理员", "导员"}, logical = Logical.OR)
    @RequestMapping("/getDormOptions.do")
    @ResponseBody
    public Result getDormOptions() throws DormException {
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
    public Result getDormByUserId(String userId) throws DormException {
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
    public Result getDormFriendByUserId(String userId) throws DormException {
        List<Student> studentList = dormService.getDormFriendByUserId(userId);
        return new Result().setCode(200).setMessage("查询室友列表成功").setData(studentList);
    }

}
