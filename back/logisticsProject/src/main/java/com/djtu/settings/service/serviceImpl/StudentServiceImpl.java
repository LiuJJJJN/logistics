package com.djtu.settings.service.serviceImpl;

import com.djtu.exception.RegisterException;
import com.djtu.exception.UserManagerException;
import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public void registerStudentUserNameVerify(String username) throws RegisterException {
        Student student = studentDao.getStudentByUsername(username);
        if (student != null) {
            throw new RegisterException("用户名已被注册");
        }
    }

    @Override
    public Integer getStudentTotal() {
        return studentDao.getStudentTotal();
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentDao.getStudentByUsername(username);
    }

    @Override
    public synchronized int editStudent(Student student) {
        Student oldStudent = studentDao.getStudentById(student.getId());

        int usernameCount = studentDao.getUsernameCountByUsername(student.getUsername());
        int snoCount = studentDao.getSnoCountBySno(student.getSno());

        if (oldStudent.getUsername().equals(student.getUsername()) || usernameCount == 0 &&
                oldStudent.getSno().equals(student.getSno()) || snoCount == 0) {

            return studentDao.editStudent(student);
        } else {
            return 0;
        }
    }

    @Override
    public String getStudentSaltById(String id) {
        return studentDao.getStudentSaltById(id);
    }

    @Override
    public void editStudentRemarkById(String id, String remark) throws UserManagerException {
        int res = studentDao.editStudentRemarkById(id, remark);
        if (res != 1) {
            throw new UserManagerException("学生备注修改失败");
        }
    }

    @Override
    public void setAvatarPath(String username, String avatarPath) throws UserManagerException {
        int res = studentDao.setStudentAvatarPathByUsername(username, avatarPath);
        if (res != 1) {
            throw new UserManagerException("学生头像设置失败");
        }
    }

}
