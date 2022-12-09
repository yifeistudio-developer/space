package com.yifeistudio.space.unit.model;

import java.io.Serializable;

/**
 * 2元，元组
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:52
 **/
public record Tuple<L, R>(L left, R right) implements Serializable {

    public static <T, R> Tuple<T, R> of(T left, R right) {
        return new Tuple<>(left, right);
    }

}
