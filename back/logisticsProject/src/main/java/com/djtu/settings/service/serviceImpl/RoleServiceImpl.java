package com.djtu.settings.service.serviceImpl;

import com.djtu.settings.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    public Set<String> getRoleByUsername(String username) {
        HashSet<String> roles = new HashSet<>();
        roles.add("stu");
        return roles;
    }

}
