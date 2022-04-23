package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.RoleDao;
import com.djtu.settings.pojo.Role;
import com.djtu.settings.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleListByUserId(String userId) {
        List<Role> roleList = roleDao.getRoleListByUserId(userId);
        System.out.println("roleList"+roleList);
        return roleList;
    }

}
