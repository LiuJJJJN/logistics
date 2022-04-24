package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.AdminDao;
import com.djtu.settings.dao.UserDao;
import com.djtu.settings.pojo.Admin;
import com.djtu.settings.service.AdminService;
import com.djtu.settings.pojo.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao userDao;

    @Override
    public UserVo getUserVoByUsername(String username) {
        Admin admin = adminDao.getAdminByUsername(username);
        String userId = userDao.getUserIdByAdminId(admin.getId());
        if (admin == null) {
            return null;
        }
        return new UserVo(userId, admin.getUsername(), admin.getPassword(), admin.getSalt(), admin.getName(),
                "", "", "");
    }

    @Override
    public String getUserIdByAdminId(String id) {
        return userDao.getUserIdByAdminId(id);
    }

}
