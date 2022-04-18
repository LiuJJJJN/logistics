package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.pojo.Role;
import com.djtu.settings.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<String> getRoleByUsername(String username) {
        HashSet<String> roles = new HashSet<>();
        roles.add("学生");
//        roles.add(new Role("2", "导员"));
//        roles.add(new Role("3", "管理员"));
        return roles;
    }

}
