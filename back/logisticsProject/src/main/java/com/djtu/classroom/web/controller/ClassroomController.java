package com.djtu.classroom.web.controller;

import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;
import com.djtu.classroom.service.ClassroomService;
import com.djtu.exception.ClassroomException;
import com.djtu.response.Result;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    ClassroomService classroomService;

    /**
     * 获取教室信息列表
     * @param map 携带pageNo和pageSize
     * @return 是否获取教室列表成功
     * @Exception
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getClassroomL.do")
    @ResponseBody
    public Result getClassroomList(@RequestBody Map map) throws ClassroomException {
        Map<String,Object> resultMap=classroomService.getClassroomList(map);
        return new Result().setCode(200).setMessage("获取教室列表成功").setData(resultMap);
    }

    /**
     * 修改教室信息
     * @param map 修改后的信息集合
     * @return 是否修改教室信息成功
     * @throws ClassroomException
     */
    @RequiresRoles("管理员")
    @RequestMapping("/editClassroomInf.do")
    @ResponseBody
    public Result editClassroomInf(@RequestBody Map map) throws ClassroomException{
        classroomService.editClassroomInf(map);
        return new Result().setCode(200).setMessage("修改教室信息成功");
    }

    /**
     * 删除教室信息
     * @param map 携带删除id的map集合
     * @return 是否删除成功
     * @throws ClassroomException
     */
    @RequiresRoles("管理员")
    @RequestMapping("/deleteClassroom.do")
    @ResponseBody
    public Result deleteClassroom(@RequestBody Map map) throws ClassroomException{
        classroomService.deleteClassroom(map);
        return new Result().setCode(200).setMessage("删除教室信息成功");
    }

    /**
     * 获取教学楼列表
     * @return 教学楼楼宇是否获得成功
     * @throws ClassroomException
     */
    @RequiresRoles("管理员")
    @RequestMapping("/getBuildingList.do")
    @ResponseBody
    public Result getBuildingList()throws ClassroomException{
        List<BuildingValueVo> list=classroomService.getBuildingList();
        return new Result().setCode(200).setMessage("获取楼宇成功").setData(list);
    }

    /**
     *
     * @param map
     * @return
     */
    @RequiresRoles("管理员")
    @RequestMapping("/addClassroomInf.do")
    @ResponseBody
    public Result addClassroomInf(@RequestBody Map map) throws ClassroomException{
        classroomService.addClassroomInf(map);
        return new Result().setCode(200).setMessage("添加成功");
    }

}
