package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Student;
import com.djtu.settings.service.StudentService;
import com.djtu.settings.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserVo getUserVoByUsername(String username) {
        Student student = studentDao.getStudentByUsername(username);
        String userId = userDao.getUserIdByStudentId(student.getId());
        if (student == null) {
            return null;
        }
        return new UserVo(userId, student.getUsername(), student.getPassword(), student.getSalt(), student.getName(),
                student.getSex(), student.getRemark(), student.getAvatarPath());
    }

    @Override
    public String getUserIdByStudentId(String id) {
        return userDao.getUserIdByStudentId(id);
    }

}
