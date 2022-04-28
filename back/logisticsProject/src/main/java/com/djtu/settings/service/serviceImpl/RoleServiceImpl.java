package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.RoleDao;
import com.djtu.settings.pojo.Role;
import com.djtu.settings.service.RoleService;
import com.djtu.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getRoleListByUserId(String userId) {
        return roleDao.getRoleListByUserId(userId);
    }

    @Override
    public int addUserRole(String userId, Integer roleId, String id) {
        return roleDao.addUserRole(userId, roleId, id);
    }

    @Override
    public int removeUserRole(String userId, Integer roleId) {
        return roleDao.removeUserRole(userId, roleId);
    }

    @Override
    public Set<String> getRolesByUserId(String userId) {
        return roleDao.getRoleSetByUserId(userId);
    }

}
