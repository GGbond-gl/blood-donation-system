package com.sdut.blood.common.utils;

import java.util.regex.Pattern;

/**
 * 手机号格式校验工具类
 * 适配档案录入、信息修改时的手机号校验需求
 */
public class PhoneUtil {

    /**
     * 中国大陆11位手机号正则
     */
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3-9]\\d{9}$");

    /**
     * 校验手机号格式是否合法
     *
     * @param phone 手机号
     * @return true-合法 false-不合法
     */
    public static boolean isValid(String phone) {
        if (phone == null || phone.trim().length() != 11) {
            return false;
        }
        return PHONE_PATTERN.matcher(phone.trim()).matches();
    }
}