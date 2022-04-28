package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.settings.pojo.Role;
import com.djtu.settings.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Object> getPermissionListByRoleList(List<Role> roleList)  {
        List<Object> list = new ArrayList<>();
        for (Role role : roleList) {
            Map<String, Object> map = new HashMap<>();
            List<Object> list1 = new ArrayList<>();
            List<Permission> fatherList = permissionDao.getFatherPermissionListByRoleId(role.getId());
            for (Permission permission : fatherList) {
                Map<String, Object> fatherMap = new HashMap<>();
                List<Permission> subList = permissionDao.getSubPermissionListByFatherId(permission.getId());
                fatherMap.put("name", permission.getName());
                fatherMap.put("subMenu", subList);
                list1.add(fatherMap);
            }
            map.put("role", role.getName()+"相关功能");
            map.put("fatherMenu", list1);
            list.add(map);
        }

        return list;
    }

}
