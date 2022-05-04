package com.djtu.settings.controller;

import com.djtu.settings.dao.PermissionDao;
import com.djtu.settings.pojo.Permission;
import com.djtu.utils.StringUtil;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TestControllerTest {

    @Resource
    private PermissionDao permissionDao;

    @Test
    public void testNowTime(){
        System.out.println(System.currentTimeMillis());
    }

}
