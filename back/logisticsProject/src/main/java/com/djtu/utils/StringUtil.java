package com.djtu.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class StringUtil {

    //生成 UUID
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // md5 加密
    public static String md5(String password, String salt) {
        // 校验 密码、盐 是否为空: null、“”、" "、制表符、换行符、换页符、回车
        if (StringUtils.isBlank(password) || StringUtils.isBlank(salt)) {
            return null;
        }
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024); //默认盐加在明文前
        return md5Hash.toHex();
    }

    public static Integer changeString(String string) {
        if (string != null && !StringUtils.isBlank(string)) {
            return Integer.valueOf(string);
        } else {
            return null;
        }
    }

    // 随机生成4位字符
    public static String rand4Str() {
        return RandomStringUtils.randomAscii(4);
    }

    // 获取当前日期时间
    public static String getNowDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
