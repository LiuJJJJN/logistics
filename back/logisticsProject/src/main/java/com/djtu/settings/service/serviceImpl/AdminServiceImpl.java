package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.AdminDao;
import com.djtu.settings.pojo.Admin;
import com.djtu.settings.pojo.vo.UserVo;
import com.djtu.settings.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin getAdminByUsername(String username) {
        return adminDao.getAdminByUsername(username);
    }
}
