package com.djtu.settings.controller;

import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.utils.StringUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestControllerTest {

    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void testNowTime(){
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void addPerm(){
        Permission permission = new Permission(StringUtil.generateUUID(),"" );
        permissionDao.addPerm()
    }

}
