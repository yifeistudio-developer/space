package com.yifeistudio.space.unit.model;

import com.yifeistudio.space.unit.util.Promises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 默认实现
 *
 * @author : hongyi
 * created at 2022/4/29 - 19:41
 **/
public class DefaultPromise<T> implements Promise<T> {

    /**
     * 执行成功
     */
    private final byte SUCCEED = 1;

    /**
     * 执行失败
     */
    private final byte FAILED = -1;

    /**
     * 执行标识
     */
    private volatile byte flag;


    /**
     * 正常结束结果
     */
    private T result;

    /**
     * 异常结束结果
     */
    private Throwable error;

    /**
     * 执行器
     */
    private final ExecutorService executorService;

    public DefaultPromise() {
        this.executorService = Promises.getExecutorService();
    }

    public DefaultPromise(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public static <T> Promise<T> of(Supplier<T> supplier) {
        DefaultPromise<T> defaultPromise = new DefaultPromise<>();
        defaultPromise.executorService.submit(() -> {
            try {
                defaultPromise.result = supplier.get();
            } catch (Throwable throwable) {
                defaultPromise.error = throwable;
            } finally {
                defaultPromise.flag = defaultPromise.error == null
                        ? defaultPromise.SUCCEED : defaultPromise.FAILED;
            }
        });
        return defaultPromise;
    }

    public <V> Promise<V> success(Function<? super T, ? extends V> callback) {
        DefaultPromise<V> defaultPromise = new DefaultPromise<>();
        defaultPromise.executorService.submit(() -> {
            try {
                defaultPromise.result = callback.apply(this.get());
            } catch (Throwable throwable) {
                defaultPromise.error = throwable;
            } finally {
                synchronized (this) {
                    flag = defaultPromise.error == null ? SUCCEED : FAILED;
                }
            }
        });
        return defaultPromise;
    }

    public <V> Promise<V> fail(Function<? super T, ? extends V> callback) {
        DefaultPromise<V> newPromise = new DefaultPromise<>(this.executorService);
        newPromise.flag = FAILED;
        return newPromise;
    }


    public <V> Promise<V> then(Function<? super T, ? extends V> successCallback,
                           Function<? super Throwable, Promise<T>> failCallback) {
        DefaultPromise<V> newPromise = new DefaultPromise<>(this.executorService);
        return newPromise;
    }

    public static <T> Promise<T> empty() {
        DefaultPromise<T> defaultPromise = new DefaultPromise<>();
        defaultPromise.flag = defaultPromise.SUCCEED;
        return defaultPromise;
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
        return flag != 0;
    }


    @Override
    public synchronized T get() {
        while (flag == 0) {
            try {
                wait();
                return result;
            } catch (InterruptedException ignore) {
                // ignore exception.
            }
        }
        return null;
    }

    @Override
    public synchronized T get(long timeout, TimeUnit unit) {
        return null;
    }
}
