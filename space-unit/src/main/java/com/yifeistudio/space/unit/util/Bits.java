package com.yifeistudio.space.unit.util;

/**
 * 二进制位工具
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:24
 **/
public final class Bits {

    /**
     * 添加属性
     *
     * @param attr 属性
     * @param masks 掩码
     * @return 含掩码的属性
     */
    public static int add(int attr, int... masks) {
        for (int mask : masks) {
            attr |= mask;
        }
        return attr;
    }

    /**
     * 移除属性
     *
     * @param attr 属性
     * @param masks 掩码
     * @return 不含掩码的属性
     */
    public static int remove(int attr, int... masks) {
        for (int mask : masks) {
            attr = (~mask) & attr;
        }
        return attr;
    }

    /**
     * 判断属性是否包含指定掩码
     *
     * @param attr 属性
     * @param mask 掩码
     * @return 属性是否含有掩码
     */
    public static boolean hasMask(int attr, int mask) {
        return (attr & mask) == mask;
    }

}
///～