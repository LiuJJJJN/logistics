package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.dao.DicValueDao;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.service.DicValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueDao dicValueDao;

    private static final Integer FLAG_NUM=1;

    @Override
    public List<DicValue> getCollegeList() {
        return dicValueDao.getCollegeList();
    }

    @Override
    public List<DicValue> getDicValuesList() throws DictionaryException {
        List<DicValue> list=dicValueDao.getDicValueList();
        if(list.isEmpty()){
            throw new DictionaryException("获取失败");
        }
        return list;
    }

    @Override
    public void setDicValues(DicValue dicValue) throws DictionaryException{
        Integer num=dicValueDao.insertDicValue(dicValue);
        if(num<FLAG_NUM){
            throw new DictionaryException("插入数据失败");
        }
    }

    @Override
    public void delDicValues(String id) throws DictionaryException{
        Integer num=dicValueDao.deleteDicValue(id);
        if(num<FLAG_NUM){
            throw new DictionaryException("删除数据失败");
        }
    }

    @Override
    public void updateDicValues(String value, String id) throws DictionaryException{
        Integer num=dicValueDao.updateDicValue(value,id);
        if(num<FLAG_NUM){
            throw new DictionaryException("修改数据失败");
        }
    }
}
