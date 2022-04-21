package com.djtu.settings.controller;

import com.djtu.utils.StringUtil;
import org.junit.Test;

public class UserControllerTest {

    @Test
    public void testMD5(){
        System.out.println(StringUtil.md5("123", "<|IS", 1024));
    }
}
