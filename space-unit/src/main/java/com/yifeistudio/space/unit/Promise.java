package com.yifeistudio.space.unit;

import com.yifeistudio.space.unit.model.Constants;

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
    default <V> Promise<V> resolve(Function<? super T, ? extends V> callback) {
        return then(callback, null);
    }

    /**
     * 当线程任务执行失败时-执行回调
     *
     * @param callback 回调函数
     * @param <V>      新的promise 类型
     * @return 新的promise
     */
    default <V> Promise<V> handle(Function<? super Throwable, ? extends V> callback) {
        return then(null, callback);
    }

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
     * 返回空Promise
     *
     * @return <空>Promise
     * @param <T> 类型
     */
    static <T> Promise<T> empty() {
        return new DefaultPromise<>(Constants.SUCCEED);
    }

    /**
     * Resolve
     *
     * @param value 结果
     * @return 返回已成功结束Promise
     * @param <V> 结果类型
     */
    static <V> Promise<V> resolve(V value) {
        return empty().resolve(val -> value);
    }

    /**
     * Reject
     *
     * @param t 异常
     * @return 返回已失败promise
     * @param <V> 结果类型
     */
    static <V> Promise<V> reject(Throwable t) {
        return new DefaultPromise<>(Constants.FAILED, t);
    }

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
