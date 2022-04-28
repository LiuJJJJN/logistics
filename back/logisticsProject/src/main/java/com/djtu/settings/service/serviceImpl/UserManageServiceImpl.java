package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.UserManagerException;
import com.djtu.settings.dao.DicValueDao;
import com.djtu.settings.dao.TutorDao;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.TutorVo;
import com.djtu.settings.service.UserManageService;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManageServiceImpl implements UserManageService {

    @Autowired
    TutorDao tutorDao;
    @Autowired
    DicValueDao dicValueDao;
    private static final Integer NUM=1;
    @Override
    public List<Tutor> getTutorList(TutorVo tutorVo) {
        List<Tutor> list=tutorDao.getTutorList(tutorVo);
        return list;
    }

    @Override
    public Integer gitTutorNum() {
        Integer num=tutorDao.getTutorNum();
        return num;
    }

    @Override
    public List<DicValue> getCollegeList() {
        List<DicValue> list=dicValueDao.getAllCollege();
        return list;
    }

    @Override
    @Transactional(rollbackFor={UserManagerException.class})
    public void resetTutorPwd(List<String> data) throws UserManagerException {
        //重置密码即密码初始化为000000
        List<Tutor> list=new ArrayList<>();
        int i=0;
        for(String s:data){
            String salt = StringUtil.rand4Str();
            Tutor tutor=new Tutor();
            tutor.setId(s);
            tutor.setSalt(salt);
            tutor.setPassword("000000");
            tutor.setPassword(StringUtil.md5(tutor.getPassword(), salt));
            tutorDao.resetPwdById(tutor);
            i++;
        }
        if(data.size()!=i){
            throw new UserManagerException("重置失败");
        }
    }

    @Override
    @Transactional(rollbackFor={UserManagerException.class})
    public void delTutorList(List<String> data) throws UserManagerException {
        Integer i=tutorDao.delTutorList(data);
        System.out.println("输出"+i);
       /* if(i!=data.size()){
            throw new UserManagerException("删除失败");
        }*/
    }

    @Override
    @Transactional(rollbackFor={UserManagerException.class})
    public void addOrUpTutorRemark(Tutor tutor) throws UserManagerException{
        Integer n=tutorDao.addOrUpTutorRemark(tutor);
        if(n<NUM){
            throw new UserManagerException("修改成功");
        }
    }
}
