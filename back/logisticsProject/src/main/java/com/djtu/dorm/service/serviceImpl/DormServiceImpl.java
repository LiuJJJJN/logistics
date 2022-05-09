package com.djtu.dorm.service.serviceImpl;

import com.djtu.building.dao.BuildingDao;
import com.djtu.building.pojo.Building;
import com.djtu.dorm.dao.DormDao;
import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.exception.NothingException;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
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
    @Autowired
    private UserDao userDao;

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
        UserVo userVo = (UserVo) SecurityUtils.getSubject().getSession().getAttribute("userVo");
        String dormId = dormDao.getDormIdByUserId(userVo.getUserId());
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
                if (dorm.getId().equals(dormId)) {
                    subMap.put("disabled", true);
                }

                subList.add(subMap);
            }
            fatherMap.put("children", subList);

            fatherList.add(fatherMap);
        }

        return fatherList;
    }

    @Override
    public Dorm getDormByUserId(String userId) throws NothingException {
        Dorm dorm = dormDao.getDormByUserId(userId);
        if (dorm == null) {
            throw new NothingException("未查询到寝室信息");
        }
        return dorm;
    }

    @Override
    public List<Student> getDormFriendByUserId(String userId) throws NothingException {
        List<Student> dormFriends = dormDao.getDormFriendByUserId(userId);
        if (dormFriends.isEmpty()) {
            throw new NothingException("获取室友信息失败或暂无室友");
        }
        return dormFriends;
    }

    @Override
    public void addChangeDormApply(String stuId, String fromDorm, String toDorm) throws DormException {
        int count = dormDao.getApplyCountByStudentId(stuId);
        if (count >= 1) {
            throw new DormException("换寝申请失败: 存在未通过的申请");
        }
        int res = dormDao.addChangeDormApply(StringUtil.generateUUID(), stuId, fromDorm, toDorm, 1);
        if (res != 1) {
            throw new DormException("换寝申请失败");
        }
    }

    @Override
    public Integer getStatusByStuId(String stuId) throws NothingException {
        Integer status = dormDao.getStatusByStuId(stuId);
        if (status == null) {
            throw new NothingException("当前状态为null");
        }
        return status;
    }

}
