package com.djtu.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {

    private static final Pattern ident_pattern = Pattern.compile("学生|导员|管理员");

    public static boolean isIdent(String ident){
        if (StringUtils.isEmpty(ident)){
            return false;
        }
        Matcher matcher = ident_pattern.matcher(ident);
        return matcher.matches();
    }

}
