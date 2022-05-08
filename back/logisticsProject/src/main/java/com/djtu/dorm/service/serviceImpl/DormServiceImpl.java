package com.djtu.dorm.service.serviceImpl;

import com.djtu.building.dao.BuildingDao;
import com.djtu.building.pojo.Building;
import com.djtu.dorm.dao.DormDao;
import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DormServiceImpl implements DormService {

    @Autowired
    private DormDao dormDao;
    @Autowired
    private BuildingDao buildingDao;

    @Override
    public void addDormByDormVo(DormVo dormVo) throws DormException {
        dormVo.setId(StringUtil.generateUUID());
        int res = dormDao.addDormByDormVo(dormVo);
        if (res != 1) {
            throw new DormException("添加寝室失败");
        }
    }

    @Override
    public List<Dorm> getDormList() throws DormException {
        List<Dorm> dormList = dormDao.getDormList();
        if (dormList.isEmpty()) {
            throw new DormException("获取寝室列表失败或寝室表空");
        }
        return dormList;
    }

    @Override
    public void deleteDormByDormId(String id) throws DormException {
        int res = dormDao.deleteDormByDormId(id);
        if (res != 1) {
            throw new DormException("删除寝室失败");
        }
    }

    @Override
    public void editDormByDormVo(DormVo dormVo) throws DormException {
        int res = dormDao.editDormByDormVo(dormVo);
        if (res != 1) {
            throw new DormException("修改寝室信息失败");
        }
    }

    @Override
    public List<Object> getBuildingDormOptions() {
        List<Object> fatherList = new ArrayList<>();

        List<Building> buildingList = buildingDao.getBuildingList();
        for (Building building : buildingList) {
            Map<String, Object> fatherMap = new HashMap<>();
            fatherMap.put("value", building.getId());
            fatherMap.put("label", building.getName());
            List<Object> subList = new ArrayList<>();
            List<Dorm> dormList = dormDao.getDormListByBuildingId(building.getId());
            for (Dorm dorm : dormList) {
                Map<String, Object> subMap = new HashMap<>();
                subMap.put("value", dorm.getId());
                subMap.put("label", dorm.getDoorNo());

                subList.add(subMap);
            }
            fatherMap.put("children", subList);

            fatherList.add(fatherMap);
        }

        return fatherList;
    }
}
