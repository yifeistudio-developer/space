package com.yifeistudio.space.unit.model;

import com.yifeistudio.space.unit.util.Promises;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * 默认实现
 *
 * @author : hongyi
 * created at 2022/4/29 - 19:41
 **/
public class DefaultPromise<T> implements Promise<T> {

    /**
     * 是否已完成
     */
    private boolean isDone;

    /**
     * 正常结束结果
     */
    private T result;

    /**
     * 异常结束结果
     */
    private Throwable error;


    private final ExecutorService executorService;

    public DefaultPromise() {
        this.executorService = Promises.getExecutorService();
    }

    public DefaultPromise(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Promise<T> success(Function<? super T, Promise<T>> callback) {
        return null;
    }

    public Promise<T> fail(Function<? super Throwable, Promise<T>> callback) {
        DefaultPromise<T> newPromise = new DefaultPromise<>(this.executorService);
        newPromise.isDone = true;
        return newPromise;
    }


    public Promise<T> then(Function<? super T, Promise<T>> successCallback,
                           Function<? super Throwable, Promise<T>> failCallback) {
        DefaultPromise<T> newPromise = new DefaultPromise<>(this.executorService);
        executorService.submit(() -> {
            try {
                T result = this.get();
                newPromise.result = result;
                return successCallback.apply(result);
            } catch (Throwable throwable) {
                return failCallback.apply(throwable);
            } finally {
                newPromise.isDone = true;
            }
        });
        return newPromise;
    }

    public static <T> Promise<T> empty() {
        return new DefaultPromise<>();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {

        return false;
    }


    @Override
    public boolean isCancelled() {

        return false;
    }


    @Override
    public boolean isDone() {
        return isDone;
    }


    @Override
    public T get() {

        return null;
    }


    @Override
    public T get(long timeout, TimeUnit unit) {
        return null;
    }
}
