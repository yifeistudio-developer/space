package com.yifeistudio.space.unit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 钱
 *
 * @param yuan 元
 */
public record Money(BigDecimal yuan) implements Serializable {

    /**
     * 通过元构建
     *
     * @param yuan 元
     * @return 金钱
     */
    public static Money yuan(BigDecimal yuan) {
        return new Money(yuan);
    }

    /**
     * 转换成分
     *
     * @return 分
     */
    public long getCent() {
        return yuan.multiply(BigDecimal.valueOf(100)).longValue();
    }

    /**
     * 通过分构建
     *
     * @param cent 分
     * @return 金钱
     */
    public static Money cent(long cent) {
        return new Money(BigDecimal.valueOf(cent).divide(BigDecimal.valueOf(100), 2, RoundingMode.DOWN));
    }

}
///~