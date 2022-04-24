package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.RegisterException;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.TutorDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.dao.UserRoleDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.pojo.Tutor;
import com.djtu.settings.pojo.User;
import com.djtu.settings.pojo.UserRole;
import com.djtu.settings.service.UserService;
import com.djtu.settings.pojo.vo.UserRoleVo;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private TutorDao tutorDao;
    //影响条数标准
    private static final Integer INSERT_SUCCESS_NUM=1;

    /**
     * 学生注册添加事务-异常进行回滚
     * @param student
     * @throws RegisterException
     */
    @Override
    @Transactional
    public void registerStudent(Student student) throws RegisterException {
        //先进行重名检测
        Student stu=studentDao.getStudentByUsername(student.getUsername());
        if(stu!=null){
            throw new RegisterException("用户名已被注册");
        }
        //进行学号绑定检测
        stu=studentDao.getStudentBySno(student.getSno());
        if(stu!=null){
            throw new RegisterException("学号被绑定过");
        }
        //设置user的属性
        User user=new User();
        user.setId(StringUtil.generateUUID());
        user.setStudentId(student.getId());
        //设置UserRole的属性
        UserRole userRole=new UserRole();
        userRole.setId(StringUtil.generateUUID());
        userRole.setUserId(user.getId());
        userRole.setRoleId("1");
        //用户表注入信息
        Integer userFlag=userDao.setStudentUser(user);
        //学生表注入注册信息
        Integer studentFlag=studentDao.setStudent(student);
        //用户角色表注入信息
        Integer userRoleFlag=userRoleDao.setUserRole(userRole);
        //判断表中数据是否插入成功  NUM=1
        if(userFlag<INSERT_SUCCESS_NUM || studentFlag<INSERT_SUCCESS_NUM  || userRoleFlag<INSERT_SUCCESS_NUM ){
            throw new RegisterException("注册失败");
        }
    }

    @Override
    @Transactional
    public void registerTutor(Tutor tutor) throws RegisterException {
        //先进行重名检测
        Tutor tutor1=tutorDao .getTutorByUsername(tutor.getUsername());
        if(tutor1!=null){
            throw new RegisterException("用户名已被注册");
        }
        //设置user的属性
        User user=new User();
        user.setId(StringUtil.generateUUID());
        user.setTutorId(tutor.getId());
        //设置UserRole的属性
        UserRole userRole=new UserRole();
        userRole.setId(StringUtil.generateUUID());
        userRole.setUserId(user.getId());
        userRole.setRoleId("2");
        //用户表注入信息
        Integer userFlag=userDao.setTutorUser(user);
        //教职工表注入注册信息
        Integer tutorFlag=tutorDao.setTutor(tutor);
        //用户角色表注入信息
        Integer tutorRoleFlag=userRoleDao.setUserRole(userRole);
        //判断表中数据是否插入成功  NUM=1
        if(userFlag<INSERT_SUCCESS_NUM || tutorFlag<INSERT_SUCCESS_NUM  || tutorRoleFlag<INSERT_SUCCESS_NUM ){
            throw new RegisterException("注册失败");
        }
    }

    @Override
    public List<UserRoleVo> getStudentUserRoleVoList(int pageCount, int pageSize) {

        List<UserRoleVo> studentUserRoleVoList = userDao.getStudentUserRoleVoList(pageCount, pageSize);
        return studentUserRoleVoList;
    }

    public void registerTutorUserNameVerify(String username) throws RegisterException{
        Tutor tutor=tutorDao.getTutorByUsername(username);
        if(tutor!=null){
            throw new RegisterException("用户名已被注册");
        }
    }

    @Override
    public void registerStudentUserNameVerify(String username) throws RegisterException {
        Student student=studentDao.getStudentByUsername(username);
        if(student!=null){
            throw new RegisterException("用户名已被注册");
        }
    }
}
