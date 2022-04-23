package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.DicTypeDao;
import com.djtu.settings.dao.DicValueDao;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicTypeDao dicTypeDao;
    @Autowired
    private DicValueDao dicValueDao;

    @Override
    public List<DicValue> getCollegeList() {
        List<DicValue> list=dicTypeDao.getCollegeList();
        return list;
    }
}
