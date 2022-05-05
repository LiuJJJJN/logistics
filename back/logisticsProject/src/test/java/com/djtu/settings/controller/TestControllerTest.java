package com.djtu.settings.controller;

import com.djtu.permission.dao.PermissionDao;
import org.junit.Test;

import javax.annotation.Resource;

public class TestControllerTest {

    @Resource
    private PermissionDao permissionDao;

    @Test
    public void testNowTime(){
        System.out.println(System.currentTimeMillis());
    }

}
