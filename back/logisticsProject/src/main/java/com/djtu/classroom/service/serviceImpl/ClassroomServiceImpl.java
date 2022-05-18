package com.djtu.classroom.service.serviceImpl;

import com.djtu.building.dao.BuildingDao;
import com.djtu.building.pojo.Building;
import com.djtu.building.pojo.vo.BuildingValueVo;
import com.djtu.classroom.dao.ClassroomDao;
import com.djtu.classroom.pojo.Classroom;
import com.djtu.classroom.pojo.vo.BuildingClassroomVo;
import com.djtu.classroom.service.ClassroomService;
import com.djtu.exception.ClassroomException;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomDao classroomDao;

    @Autowired
    private BuildingDao buildingDao;

    private static final Integer SUCCESS_FLAG=1;

    @Override
    @Transactional(rollbackFor = {ClassroomException.class})
    public Map<String, Object> getClassroomList(Map map) throws ClassroomException {
        Integer pageNo=(Integer)map.get("pageNo");
        Integer pageSize=(Integer)map.get("pageSize");
        //获取所有教室以及教室所在楼宇信息
        List<BuildingClassroomVo> list=classroomDao.getClassroomAllList(pageNo,pageSize);
        if(list.isEmpty()){
            throw new ClassroomException("获取失败");
        }
        //获取总条数
        Integer total=classroomDao.getClassroomAllTotal();
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("list",list);
        resultMap.put("total",total);
        return resultMap;
    }

    @Override
    public void editClassroomInf(Map map) throws ClassroomException{
        String id=(String)map.get("id");
        String electInf=(String)map.get("electInf");
        String isUse=(String)map.get("isUse");
        String isOpen=(String)map.get("isOpen");
        String isClean=(String)map.get("isClean");
        Classroom classroom=new Classroom();
        classroom.setId(id);
        classroom.setElectInf(electInf);
        classroom.setIsUse(isUse);
        classroom.setIsOpen(isOpen);
        classroom.setIsClean(isClean);
        Integer n=classroomDao.editClassroomInfById(classroom);
        if(n<SUCCESS_FLAG){
            throw new ClassroomException("修改异常");
        }
    }

    @Override
    public void deleteClassroom(Map map) throws ClassroomException {
        String id=(String)map.get("id");
        System.out.println("!!!!!"+id);
        Integer n=classroomDao.deleteClassroomById(id);
        if(n<SUCCESS_FLAG){
            throw new ClassroomException("删除异常");
        }
    }

    @Override
    public List<BuildingValueVo> getBuildingList() throws ClassroomException{
        List<BuildingValueVo> list=buildingDao.getBuildingValueListByType("教学楼");
        if(list.isEmpty()){
            throw new ClassroomException("获取教学楼楼宇列表失败");
        }
        return list;
    }

    @Override
    public void addClassroomInf(Map map) throws ClassroomException{
        Classroom classroom=new Classroom();
        classroom.setId(StringUtil.generateUUID());
        classroom.setRoomNo((String)map.get("roomNo"));
        classroom.setBuildingId((String)map.get("buildingId"));
        classroom.setElectInf((String)map.get("electInf"));
        classroom.setIsUse((String)map.get("isUse"));
        classroom.setIsOpen((String)map.get("isOpen"));
        classroom.setIsClean((String)map.get("isClean"));
        Integer n=classroomDao.addClassroomInf(classroom);
        if(n<SUCCESS_FLAG){
            throw new ClassroomException("删除异常");
        }
    }
}
