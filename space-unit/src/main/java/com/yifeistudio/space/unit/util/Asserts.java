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
        isTrue(condition, express, () -> {throw exp;}, exp);
    }

    /**
     * 断言表达式为真
     *
     * @param express 表达式
     * @param runner 断言表达式为真
     * @param exp 断言表达式为真
     */
    public static void isTrue(boolean express, Runnable runner, RuntimeException exp) {
        isTrue(true, express, runner, exp);
    }

    /**
     * 断言表达式为真
     *
     * @param express 表达式
     * @param msg 信息
     * @param runner 断言表达式为真
     */
    public static void isTrue(boolean express, String msg, Runnable runner) {
        isTrue(true, express, runner, new AssertException(msg));
    }

    /**
     * 断言表达式为真
     *
     * @param condition 条件
     * @param express 表达式
     * @param msg 信息
     * @param runner 抛出异常前处理
     */
    public static void isTrue(boolean condition, boolean express, String msg, Runnable runner) {
        isTrue(condition, express, runner, new AssertException(msg));
    }

    /**
     * 断言表达式为真
     *
     * @param condition 激活条件
     * @param express 表达式
     * @param runner 执行任务
     */
    public static void isTrue(boolean condition, boolean express, Runnable runner, RuntimeException exp) {
        if (condition) {
            if (!express) {
                if (runner != null) {
                    runner.run();
                }
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
        notNull(condition, obj, null, exp);
    }


    /**
     * 非空判断 重载
     * @param condition 条件
     * @param obj 对象
     * @param msg 信息
     * @param runner 抛出异常前处理
     */
    public static void notNull(boolean condition, Object obj, String msg, Runnable runner) {
        notNull(condition, obj, runner, new AssertException(msg));
    }

    /**
     * 非空断言判断 重载
     *
     * @param obj 判断对象
     * @param msg 信息
     * @param runner 抛出异常前处理
     */
    public static void notNull(Object obj, String msg, Runnable runner) {
        notNull(true, obj, runner, new AssertException(msg));
    }

    /**
     * 非空断言判断
     *
     * @param condition 条件
     * @param obj 判断对象
     * @param runner 抛出异常前处理
     * @param exp 抛出异常
     */
    public static void notNull(boolean condition, Object obj, Runnable runner, RuntimeException exp) {
        if (!condition) {
            return;
        }
        isTrue(obj != null, runner, exp);
        if (obj instanceof String str) {
            String trimStr = str.trim();
            isTrue(!trimStr.isEmpty(), runner, exp);
            return;
        }
        if (obj instanceof Collection<?> col) {
            isTrue(!col.isEmpty(), runner, exp);
            return;
        }
        if (obj instanceof Map<?, ?> map) {
            isTrue(!map.isEmpty(), runner, exp);
        }
    }

}
///~