package com.djtu.building.web.controller;

import com.djtu.building.pojo.Building;
import com.djtu.building.service.BuildingService;
import com.djtu.exception.BuildingException;
import com.djtu.response.Result;
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
@RequestMapping("/building")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    /**
     * 添加楼宇接口
     *
     * @param building 楼宇信息
     * @return 添加成功
     * @throws BuildingException 添加失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/addBuilding.do")
    @ResponseBody
    public Result addBuilding(@RequestBody Building building) throws BuildingException {
        buildingService.addBuilding(building);
        return new Result().setCode(200).setMessage("添加楼宇成功");
    }

    /**
     * 修改楼宇接口
     *
     * @param building 楼宇信息
     * @return 修改成功
     * @throws BuildingException 修改失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/editBuilding.do")
    @ResponseBody
    public Result editBuilding(@RequestBody Building building) throws BuildingException {
        buildingService.editBuilding(building);
        return new Result().setCode(200).setMessage("修改楼宇成功");
    }

    /**
     * 删除楼宇接口
     *
     * @param map 楼宇id
     * @return 删除成功
     * @throws BuildingException 删除失败
     */
    @RequiresRoles("管理员")
    @RequestMapping("/deleteBuilding.do")
    @ResponseBody
    public Result deleteBuilding(@RequestBody Map map) throws BuildingException {
        String id = (String) map.get("id");
        buildingService.deleteBuildingById(id);
        return new Result().setCode(200).setMessage("删除楼宇成功");
    }

    /**
     * 获取全部楼宇列表接口
     *
     * @return 楼宇列表
     * @throws BuildingException 获取楼宇列表异常
     */
    @RequiresRoles(value = {"学生", "导员", "管理员"}, logical = Logical.OR)
    @RequestMapping("/getBuildingList.do")
    @ResponseBody
    public Result getBuildingList() throws BuildingException {
        List<Building> buildingList = buildingService.getBuildingList();
        return new Result().setCode(200).setMessage("获取楼宇列表成功").setData(buildingList);
    }

}
