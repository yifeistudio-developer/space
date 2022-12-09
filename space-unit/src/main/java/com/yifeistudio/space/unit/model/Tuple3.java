package com.yifeistudio.space.unit.model;

import java.io.Serializable;

/**
 * 3元，元组
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:52
 **/
public record Tuple3<L,M, R>(L left, M middle, R right) implements Serializable {

    public static <L, M, R> Tuple3<L, M, R> of(L left, M middle, R right) {
        return new Tuple3<>(left, middle, right);
    }

}
