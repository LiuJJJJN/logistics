package com.djtu.building.service.serviceImpl;

import com.djtu.building.dao.BuildingDao;
import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;
import com.djtu.building.service.BuildingService;
import com.djtu.exception.BuildingException;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingDao buildingDao;

    @Override
    public void addBuilding(Building building) throws BuildingException {
        building.setId(StringUtil.generateUUID());
        int res = buildingDao.addBuilding(building);
        if (res != 1) {
            throw new BuildingException("添加楼宇失败");
        }
    }

    @Override
    public List<Building> getBuildingList() throws BuildingException {
        List<Building> buildingList = buildingDao.getBuildingList();
        if (buildingList.isEmpty()) {
            throw new BuildingException("获取楼宇列表失败或暂无楼宇信息");
        }
        return buildingList;
    }

    @Override
    public void editBuilding(Building building) throws BuildingException {
        int res = buildingDao.editBuilding(building);
        if (res != 1) {
            throw new BuildingException("修改楼宇信息失败");
        }
    }

    @Override
    public void deleteBuildingById(String id) throws BuildingException {
        int res = buildingDao.deleteBuildingById(id);
        if (res != 1) {
            throw new BuildingException("删除楼宇失败");
        }
    }

    @Override
    public List<BuildingValueVo> getBuildingValueListByType(String type) throws BuildingException {
        List<BuildingValueVo> buildingValueList = buildingDao.getBuildingValueListByType(type);
        if (buildingValueList.isEmpty()) {
            throw new BuildingException("获取楼宇Value列表失败或暂无楼宇信息");
        }
        return buildingValueList;
    }

}
