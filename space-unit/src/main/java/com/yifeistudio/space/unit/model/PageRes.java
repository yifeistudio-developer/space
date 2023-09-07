package com.yifeistudio.space.unit.model;

import java.io.Serializable;
import java.util.Collection;

/**
 * 分页信息
 *
 * @author : hongyi
 * created at 2022/10/18 - 14:38
 **/
public record PageRes<T>(int count, Collection<T> records) implements Serializable {
    public static <T> PageRes<T> of(int count, Collection<T> records) {
        return new PageRes<>(count, records);
    }
}
