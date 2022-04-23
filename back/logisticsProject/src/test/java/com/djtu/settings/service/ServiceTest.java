package com.djtu.settings.service;

import com.djtu.settings.service.serviceImpl.UserServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testMd5(){
        Md5Hash md5Hash = new Md5Hash("123456", "", 1024);
        System.out.println(md5Hash.toHex());
    }
}
