package com.yifeistudio.space.unit.util;

import java.util.regex.Pattern;

/**
 * 模式匹配
 *
 * @author yi
 * <p>
 * Created at 2024/4/29 上午10:29
 * @since 1.0
 */
public final class Matchers {

    private Matchers() {}

    /**
     * 邮箱格式匹配
     * @param str 字符串
     * @return 匹配结果
     */
    public static boolean isEmail(String str) {
        String emailPattern = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.matches(emailPattern, str);
    }

    /**
     * 手机号格式匹配
     * @param str 字符串
     * @return 匹配结果
     */
    public static boolean isPhone(String str) {
        String phonePattern = "^1[3456789]\\d{9}$";
        return Pattern.matches(phonePattern, str);
    }


}
