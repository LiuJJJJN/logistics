package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.DictionaryException;
import com.djtu.settings.dao.DicTypeDao;
import com.djtu.settings.dao.DicValueDao;
import com.djtu.settings.pojo.DicType;
import com.djtu.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DicTypeDao dicTypeDao;
    @Autowired
    private DicValueDao dicValueDao;
    private static final int Flag_NUM=1;
    @Override
    public List<DicType> getDicTypeList() throws DictionaryException{
        List<DicType> list=dicTypeDao.getDicTypeList();
        if(list.isEmpty()){
            throw new DictionaryException("获取失败");
        }
        return list;
    }

    @Override
    public void setDicType(DicType dicType) throws DictionaryException{
        Integer i=dicTypeDao.setDicType(dicType);
        if(i<Flag_NUM){
            throw new DictionaryException("添加失败");
        }
    }

    @Override
    public void delDicType(String code) throws DictionaryException{
        Integer i=dicTypeDao.delDicType(code);
        if(i<Flag_NUM){
            throw new DictionaryException("删除失败");
        }
    }

    @Override
    @Transactional
    public void updateDicType(DicType dicType) throws DictionaryException{
        //uuid
        String newCode=dicType.getCode();
        //获取原来的code
        String oldCode=dicTypeDao.getCodeById(dicType.getId());
        //更新DicType的code
        Integer i=dicTypeDao.updateDicType(dicType);
        //更新DicValue表的type_code
        Integer flag=dicValueDao.updateDicValueOfCode(oldCode,newCode);
        if(i<Flag_NUM || flag<Flag_NUM){
            throw new DictionaryException("更新失败");
        }
    }
}
