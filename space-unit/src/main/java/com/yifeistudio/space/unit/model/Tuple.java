package com.yifeistudio.space.unit.model;

import java.io.Serializable;

/**
 * 元组
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:52
 **/
public class Tuple<L, R> implements Serializable {

    private L left;
    private R right;

    public Tuple(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public static <T, R> Tuple<T, R> of(T left, R right) {
        return new Tuple<>(left, right);
    }


    //---------- getter setter ----------


    public L getLeft() {
        return left;
    }

    public void setLeft(L left) {
        this.left = left;
    }

    public R getRight() {
        return right;
    }

    public void setRight(R right) {
        this.right = right;
    }
}
