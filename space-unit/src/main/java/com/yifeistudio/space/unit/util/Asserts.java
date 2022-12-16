package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.AssertException;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具
 *
 * @author : hongyi
 * created at 2022/4/15 - 13:30
 **/
public final class Asserts {

    private Asserts() {}

    /**
     * 断言表达式为真
     *
     * @param express 表达式
     * @param msg 错误信息
     */
    public static void isTrue(boolean express, String msg) {
        isTrue(true, express, msg);
    }

    /**
     * 断言表达式为真
     *
     * @param condition 激活条件
     * @param express 表达式
     * @param msg 错误信息
     */
    public static void isTrue(boolean condition, boolean express, String msg) {
        isTrue(condition, express, new AssertException(msg));
    }

    /**
     * 断言表达式为真
     *
     * @param express 表达式
     * @param exp 异常信息
     */
    public static void isTrue(boolean express, RuntimeException exp) {
        isTrue(true, express, exp);
    }

    /**
     * 断言表达式为真
     *
     * @param condition 激活条件
     * @param express 表达式
     * @param exp 异常
     */
    public static void isTrue(boolean condition, boolean express, RuntimeException exp) {
        if (condition) {
            if (!express) {
                throw exp;
            }
        }
    }

    /**
     * 断言非空
     *
     * @param obj 断言对象
     * @param msg 错误信息
     */
    public static void notNull(Object obj, String msg) {
        notNull(obj, new AssertException(msg));
    }

    /**
     * 断言非空
     * @param condition 激活条件
     * @param obj 断言对象
     * @param msg 错误信息
     */
    public static void notNull(boolean condition, Object obj, String msg) {
        notNull(condition, obj, new AssertException(msg));
    }

    /**
     * 断言非空
     *
     * @param obj 断言对象
     * @param exp 异常信息
     */
    public static void notNull(Object obj, RuntimeException exp) {
        notNull(true, obj, exp);
    }

    /**
     * 断言非空
     *
     * @param condition 激活条件
     * @param obj 断言对象
     * @param exp 异常信息
     */
    public static void notNull(boolean condition, Object obj, RuntimeException exp) {
        if (!condition) {
            return;
        }
        isTrue(obj != null, exp);
        if (obj instanceof String str) {
            String trimStr = str.trim();
            isTrue(trimStr.length() > 0, exp);
            return;
        }
        if (obj instanceof Collection<?> col) {
            isTrue(!col.isEmpty(), exp);
            return;
        }
        if (obj instanceof Map<?, ?> map) {
            isTrue(!map.isEmpty(), exp);
        }
    }

}
///~