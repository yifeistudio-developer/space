package com.yifeistudio.space.unit.model;

/**
 * 元组
 *
 * @author : hongyi
 * created at 2022/4/22 - 10:52
 **/
public class Tuple<T, R> {

    private T t;
    private R r;

    public Tuple(T t, R r) {
        this.t = t;
        this.r = r;
    }

    public static <T, R> Tuple<T, R> of(T t, R r) {
        return new Tuple<>(t, r);
    }


    //---------- getter setter ----------

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }
}
