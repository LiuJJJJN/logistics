package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.RegisterException;
import com.djtu.settings.dao.*;
import com.djtu.settings.pojo.*;
import com.djtu.settings.pojo.vo.*;
import com.djtu.settings.service.UserService;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    //影响条数标准
    private static final Integer INSERT_SUCCESS_NUM = 1;
    //学生默认角色id
    private static final String STUDENT_DEFAULT_ROLE_ID = "1";
    //导员默认角色id
    private static final String TUTOR_DEFAULT_ROLE_ID = "2";

    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private TutorDao tutorDao;
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    @Transactional
    public void registerStudent(Student student) throws RegisterException {
        //先进行重名检测
        Student stu = studentDao.getStudentByUsername(student.getUsername());
        if (stu != null) {
            throw new RegisterException("用户名已被注册");
        }
        //进行学号绑定检测
        stu = studentDao.getStudentBySno(student.getSno());
        if (stu != null) {
            throw new RegisterException("学号被绑定过");
        }
        //设置user的属性
        User user = new User();
        user.setId(StringUtil.generateUUID());
        user.setStudentId(student.getId());
        //设置UserRole的属性
        UserRole userRole = new UserRole();
        userRole.setId(StringUtil.generateUUID());
        userRole.setUserId(user.getId());
        userRole.setRoleId(STUDENT_DEFAULT_ROLE_ID);
        //用户表注入信息
        Integer userFlag = userDao.setStudentUser(user);
        //学生表注入注册信息
        Integer studentFlag = studentDao.setStudent(student);
        //用户角色表注入信息
        Integer userRoleFlag = userRoleDao.setUserRole(userRole);
        //判断表中数据是否插入成功  NUM=1
        if (userFlag < INSERT_SUCCESS_NUM || studentFlag < INSERT_SUCCESS_NUM || userRoleFlag < INSERT_SUCCESS_NUM) {
            throw new RegisterException("注册失败");
        }
    }

    @Override
    @Transactional
    public void registerTutor(Tutor tutor) throws RegisterException {
        //先进行重名检测
        Tutor tutor1 = tutorDao.getTutorByUsername(tutor.getUsername());
        if (tutor1 != null) {
            throw new RegisterException("用户名已被注册");
        }
        //设置user的属性
        User user = new User();
        user.setId(StringUtil.generateUUID());
        user.setTutorId(tutor.getId());
        //设置UserRole的属性
        UserRole userRole = new UserRole();
        userRole.setId(StringUtil.generateUUID());
        userRole.setUserId(user.getId());
        userRole.setRoleId(TUTOR_DEFAULT_ROLE_ID);
        //用户表注入信息
        Integer userFlag = userDao.setTutorUser(user);
        //教职工表注入注册信息
        Integer tutorFlag = tutorDao.setTutor(tutor);
        //用户角色表注入信息
        Integer tutorRoleFlag = userRoleDao.setUserRole(userRole);
        //判断表中数据是否插入成功  NUM=1
        if (userFlag < INSERT_SUCCESS_NUM || tutorFlag < INSERT_SUCCESS_NUM || tutorRoleFlag < INSERT_SUCCESS_NUM) {
            throw new RegisterException("注册失败");
        }
    }

    @Override
    public UserVo getUserVoByStudentUsername(String username) {
        Student student = studentDao.getStudentByUsername(username);
        if (student == null) {
            return null;
        }
        String userId = userDao.getUserIdByStudentId(student.getId());
        return new UserVo(userId, student.getUsername(), student.getPassword(), student.getSalt(), student.getName(),
                student.getSex(), student.getRemark(), student.getAvatarPath());
    }

    @Override
    public List<StudentRoleVo> getStudentUserRoleVoList(StudentSearchVo studentSearchVo, int pageCount, int pageSize) {

        return userDao.getStudentUserRoleVoList(studentSearchVo, pageCount, pageSize);
    }

    @Override
    public String getUserIdByAdminId(String id) {
        return userDao.getUserIdByAdminId(id);
    }

    @Override
    public UserVo getUserVoByAdminUsername(String username) {
        Admin admin = adminDao.getAdminByUsername(username);
        if (admin == null){
            return null;
        }
        String userId = userDao.getUserIdByAdminId(admin.getId());
        return new UserVo(userId, admin.getUsername(), admin.getPassword(), admin.getSalt(), admin.getName(),
                "", "", "");
    }

    @Override
    public Integer getStudentRoleListTotal(StudentSearchVo studentSearchVo) {
        return userDao.getStudentUserRoleVoListTotal(studentSearchVo);
    }

    @Override
    public List<TutorRoleVo> getTutorUserRoleVoList(TutorSearchVo tutorSearchVo, Integer pageNo, Integer pageSize) {
        return userDao.getTutorUserRoleVoList(tutorSearchVo, pageNo, pageSize);
    }

    @Override
    public Integer getTutorRoleListTotal(TutorSearchVo tutorSearchVo) {
        return userDao.getTutorUserRoleVoListTotal(tutorSearchVo);
    }

    @Override
    public UserVo getUserVoByTutorUsername(String username) {
        Tutor tutor = tutorDao.getTutorByUsername(username);
        if (tutor == null) {
            return null;
        }
        String userId = userDao.getUserIdByTutorId(tutor.getId());
        return new UserVo(userId, tutor.getUsername(), tutor.getPassword(), tutor.getSalt(), tutor.getName(),
                tutor.getSex(), tutor.getRemark(), tutor.getAvatarPath());
    }

}
