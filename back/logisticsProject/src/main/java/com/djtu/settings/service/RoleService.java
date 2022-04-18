package com.djtu.settings.service;

import com.djtu.settings.pojo.Role;

import java.util.Set;

public interface RoleService {
    Set<String> getRoleByUsername(String username);
}
