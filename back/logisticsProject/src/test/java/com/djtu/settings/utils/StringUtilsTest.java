package com.djtu.settings.utils;

import com.djtu.utils.StringUtil;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void testRand4Str(){
        System.out.println(StringUtil.rand4Str());
    }

    @Test
    public void testUUID(){
        System.out.println(StringUtil.generateUUID());
    }

    @Test
    public void testMd5(){
        System.out.println(StringUtil.md5("123123", "<|IS"));
    }

}
