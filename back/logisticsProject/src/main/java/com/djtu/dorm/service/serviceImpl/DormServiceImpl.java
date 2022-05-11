package com.djtu.dorm.service.serviceImpl;

import com.djtu.building.dao.BuildingDao;
import com.djtu.building.pojo.Building;
import com.djtu.dorm.dao.DormDao;
import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormApplyPageConditionVo;
import com.djtu.dorm.pojo.vo.DormApplyVo;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.exception.NothingException;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DormServiceImpl implements DormService {

    @Autowired
    private DormDao dormDao;
    @Autowired
    private BuildingDao buildingDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public void addDormByDormVo(DormVo dormVo) throws DormException {
        dormVo.setId(StringUtil.generateUUID());
        int res = dormDao.addDormByDormVo(dormVo);
        if (res != 1) {
            throw new DormException("添加寝室失败");
        }
    }

    @Override
    public List<Dorm> getDormList() throws NothingException {
        List<Dorm> dormList = dormDao.getDormList();
        if (dormList.isEmpty()) {
            throw new NothingException("获取寝室列表失败或寝室表空");
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
        //查询是否存在导员, 没有导员不允许换寝
        String tutorId = studentDao.getTutorIdByStuId(stuId);
        if (tutorId == null) {
            throw new DormException("换寝申请失败: 未绑定导员, 请等待导员绑定");
        }
        // 查询是否存在未完成的换寝申请
        int count = dormDao.getApplyCountByStudentId(stuId);
        if (count >= 1) {
            throw new DormException("换寝申请失败: 存在未通过的申请");
        }
        // 添加在换寝申请
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = dateFormat.format(new Date());
        int res = dormDao.addChangeDormApply(StringUtil.generateUUID(), stuId, fromDorm, toDorm, 1, time);
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

    @Override
    public List<DormApplyVo> getDormChangeApplyList(DormApplyPageConditionVo vo) throws NothingException {
        List<DormApplyVo> dormApplyVoList = dormDao.getDormChangeApplyList(vo);
        if (dormApplyVoList.isEmpty()) {
            throw new NothingException("当前暂无申请");
        }
        return dormApplyVoList;
    }

    @Override
    @Transactional
    public void finishChangeByStuId(String stuId) throws DormException {
        // 修改学生表中寝室id
        int res = studentDao.setNewDorm(stuId);
        if (res != 1) {
            throw new DormException("完成换寝失败: 学生寝室值修改失败");
        }
        // 修改换寝申请表中status值
        res = dormDao.finishChangeByStuId(stuId);
        if (res != 1) {
            throw new DormException("完成换寝失败: 换寝申请状态修改失败");
        }
    }

    @Override
    public int getDormChangeApplyTotal(DormApplyPageConditionVo vo) {
        return dormDao.getDormChangeApplyTotal(vo);
    }

    @Override
    public void setDormStatusById(String id, String status) throws DormException {
        int res = dormDao.setDormStatusById(id, status);
        if (res != 1) {
            throw new DormException("修改申请状态失败");
        }
    }

}
