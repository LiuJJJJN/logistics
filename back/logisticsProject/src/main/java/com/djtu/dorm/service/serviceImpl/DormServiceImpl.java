package com.djtu.dorm.service.serviceImpl;

import com.djtu.dorm.dao.DormDao;
import com.djtu.dorm.pojo.Dorm;
import com.djtu.dorm.pojo.vo.DormVo;
import com.djtu.dorm.service.DormService;
import com.djtu.exception.DormException;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormServiceImpl implements DormService {

    @Autowired
    private DormDao dormDao;

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
}
