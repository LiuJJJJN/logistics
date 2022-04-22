package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.StudentDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.User;
import com.djtu.settings.service.UserService;
import com.djtu.settings.vo.UserStuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;

    @Override
    public User getUserByUsername(String username) {
        return userDao.getUserByName(username);
    }

    @Override
    @Transactional
    public boolean addUserStudent(UserStuVo vo) {
//        studentDao.addStudent();
        userDao.addUser();
        return false;
    }

}
