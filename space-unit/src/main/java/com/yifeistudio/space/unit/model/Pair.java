package com.yifeistudio.space.unit.model;

import java.io.Serializable;

/**
 * 2元，元组
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:52
 **/
public record Pair<L, R>(L key, R value) implements Serializable {

    public static <T, R> Pair<T, R> of(T key, R value) {
        return new Pair<>(key, value);
    }

}
