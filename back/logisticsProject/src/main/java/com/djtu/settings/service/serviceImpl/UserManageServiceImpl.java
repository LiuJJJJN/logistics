package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.UserManagerException;
import com.djtu.settings.dao.DicValueDao;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.TutorDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.dao.UserRoleDao;
import com.djtu.settings.pojo.DicValue;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.vo.StudentSearchVo;
import com.djtu.settings.pojo.User;
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
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TutorDao tutorDao;
    @Autowired
    private DicValueDao dicValueDao;
    @Autowired
    private UserRoleDao userRoleDao;
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
        Integer flag=data.size();
        //根据id删除tutor表里的记录
        Integer delTutorNum=tutorDao.delTutorList(data);
        //user表：根据tutor_id查出id
        List<User> list=userDao.getIdByTutorId(data);
        //根据user_id删除tbl_user_role表中的记录
        Integer delUserRoleNum=userRoleDao.delByUserId(list);
        //根据id(tutor_id)删除user表里的记录
        Integer delUserNum=userDao.delByTutorId(data);
        if(delTutorNum!=flag && delUserRoleNum!=flag && delUserNum!=flag){
            throw new UserManagerException("导员删除失败");
        }
    }

    @Override
    @Transactional(rollbackFor={UserManagerException.class})
    public void addOrUpTutorRemark(Tutor tutor) throws UserManagerException{
        Integer n=tutorDao.addOrUpTutorRemark(tutor);
        if(n<NUM){
            throw new UserManagerException("修改成功");
        }
    }

    @Override
    public List<Student> getStudentList(StudentSearchVo studentSearchVo, Integer pageNo, Integer pageSize) {
        return studentDao.getStudentListByPageCondition(studentSearchVo, pageNo, pageSize);
    }

    @Override
    @Transactional(rollbackFor = UserManagerException.class)
    public void delStudentList(List<String> stuList) throws UserManagerException {
        int res = studentDao.deleteStudentByStuList(stuList);
        if (res != stuList.size()) {
            throw new UserManagerException("批量删除学生失败");
        }
        List<User> userList = userDao.getUserIdListByStudentIdList(stuList);
        userRoleDao.delByUserId(userList);
        int userRes = userDao.delUserByStudentId(userList);
        if (userRes != stuList.size()) {
            throw new UserManagerException("批量删除学生对应User失败");
        }
    }

    @Override
    public void resetStudentPwd(String id) throws UserManagerException {
        String salt = StringUtil.rand4Str();
        String password = StringUtil.md5("000000", salt);
        int res = studentDao.editStudentPwdById(id, password, salt);
        if (res != 1) {
            throw new UserManagerException("重置学生密码失败");
        }
    }

}
