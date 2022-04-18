package com.djtu.settings.service;

import java.util.Set;

public interface PermissionService {
    Set<String> getPermissionByUsername(String username);
}
