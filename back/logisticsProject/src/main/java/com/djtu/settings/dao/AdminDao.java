package com.djtu.settings.dao;

import com.djtu.settings.pojo.Admin;

public interface AdminDao {
    Admin getAdminByUsername(String username);
}
