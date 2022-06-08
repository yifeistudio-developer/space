package com.yifeistudio.space.unit.model;

import java.util.function.Function;

/**
 * Promise
 *
 * @author : hongyi
 * created at 2022/4/29 - 12:49
 **/
public interface Promise<T> {


    /**
     * 当线程任务执行成功时-执行回调
     *
     * @param callback 回调函数
     * @param <V>      新的promise 类型
     * @return 新的promise
     */
    <V> Promise<V> success(Function<? super T, ? extends V> callback);

    /**
     * 当线程任务执行失败时-执行回调
     *
     * @param callback 回调函数
     * @param <V>      新的promise 类型
     * @return 新的promise
     */
    <V> Promise<V> fail(Function<? super Throwable, ? extends V> callback);

    /**
     * 当线程任务执行成功时-执行成功/失败时回调
     *
     * @param successCallback 成功回调函数
     * @param failCallback    失败回调函数
     * @param <V>             新的promise 类型
     * @return 新的promise
     */
    <V> Promise<V> then(Function<? super T, ? extends V> successCallback,
                        Function<? super Throwable, ? extends V> failCallback);

    /**
     * 是否已经结束
     *
     * @return 是否执行结束
     */
    boolean isDone();

    /**
     * 获取执行结果
     *
     * @return 执行结果
     */
    T get();
}
