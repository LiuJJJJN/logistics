package com.djtu.dictionary.service.serviceImpl;

import com.djtu.exception.DictionaryException;
import com.djtu.dictionary.dao.DicValueDao;
import com.djtu.dictionary.pojo.DicValue;
import com.djtu.dictionary.pojo.vo.DicValueVo;
import com.djtu.dictionary.service.DicValueService;
import com.djtu.exception.NothingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueDao dicValueDao;

    private static final Integer FLAG_NUM = 1;

    @Override
    public List<DicValue> getCollegeList() {
        return dicValueDao.getCollegeList();
    }

    @Override
    public List<DicValue> getDicValuesList() throws NothingException {
        List<DicValue> list = dicValueDao.getDicValueList();
        if (list.isEmpty()) {
            throw new NothingException("获取失败");
        }
        return list;
    }

    @Override
    public List<DicValue> getDicValuesByCodeOrValue(DicValueVo dicValueVo) throws NothingException {
        List<DicValue> list = dicValueDao.getDicValuesByCodeOrName(dicValueVo);
        if (list.isEmpty()) {
            throw new NothingException("获取失败");
        }
        return list;
    }

    @Override
    public Integer getDicValuesListNum() {
        Integer num = dicValueDao.getDicValuesListNum();
        return num;
    }

    @Override
    public void setDicValues(DicValue dicValue) throws DictionaryException {
        Integer num = dicValueDao.insertDicValue(dicValue);
        if (num < FLAG_NUM) {
            throw new DictionaryException("插入数据失败");
        }
    }

    @Override
    public void delDicValues(List<String> data) throws DictionaryException {
        Integer num = dicValueDao.deleteDicValue(data);
        if (num < FLAG_NUM) {
            throw new DictionaryException("删除数据失败");
        }
    }

    @Override
    public void updateDicValues(DicValue dicValue) throws DictionaryException {
        Integer num = dicValueDao.updateDicValue(dicValue);
        if (num < FLAG_NUM) {
            throw new DictionaryException("修改数据失败");
        }
    }

    @Override
    public List<DicValue> getBuildingTypeList() throws NothingException {
        List<DicValue> buildingTypeList = dicValueDao.getBuildingTypeList();
        if (buildingTypeList.isEmpty()) {
            throw new NothingException("获取楼宇类型列表失败");
        }
        return buildingTypeList;
    }
}
