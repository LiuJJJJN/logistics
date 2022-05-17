package com.djtu.building.service;

import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;
import com.djtu.exception.BuildingException;
import com.djtu.exception.NothingException;

import java.util.List;

public interface BuildingService {

    /**
     * 添加楼宇
     *
     * @param building 楼宇信息
     */
    void addBuilding(Building building) throws BuildingException;

    /**
     * 获取楼宇列表
     *
     * @return 楼宇列表
     */
    List<Building> getBuildingList() throws NothingException;

    /**
     * 修改楼宇
     *
     * @param building 楼宇信息
     */
    void editBuilding(Building building) throws BuildingException;

    /**
     * 根据id删除楼宇信息
     *
     * @param id 楼宇id
     */
    void deleteBuildingById(String id) throws BuildingException;

    /**
     * 根据type获取楼宇value列表
     *
     * @return 楼宇value列表
     */
    List<BuildingValueVo> getBuildingValueListByType(String type) throws NothingException;
}
