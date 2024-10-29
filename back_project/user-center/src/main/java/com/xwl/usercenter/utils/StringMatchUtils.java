package com.xwl.usercenter.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ruoling
 * @date 2024/10/28 22:45:49
 * @description
 */
public class StringMatchUtils {

    /**
     * 判断字符串是否包含特殊字符
     * @param str 待判断的字符串
     * @return 包含则返回 true
     */
    public static boolean containsSpecialChar(String str) {
        String regex = "[^a-zA-Z0-9\\s!@#$%^&*()_+=-`~\\[\\]{}|;:,.<>/?]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return true;
        }
        return false;
    }
}
