package com.djtu.settings.service;

import com.djtu.settings.service.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testGetUserByUsername(){
        System.out.println(new UserServiceImpl().getUserByUsername("mike"));
    }
}
