package com.yifeistudio.space.unit.model;

import com.yifeistudio.space.unit.util.Asserts;
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
    private volatile T result;

    /**
     * 异常结束结果
     */
    private volatile Throwable error;

    /**
     * 执行器
     */
    private final ExecutorService executorService;

    public DefaultPromise() {
        this.executorService = Promises.getExecutorService();
    }

    public DefaultPromise(ExecutorService executorService) {
        Asserts.notNull(executorService, "executorService is required nonNull");
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
                synchronized (defaultPromise) {
                    defaultPromise.flag = defaultPromise.error == null
                            ? defaultPromise.SUCCEED : defaultPromise.FAILED;
                }
            }
        });
        return defaultPromise;
    }

    @Override
    public <V> Promise<V> success(Function<? super T, ? extends V> callback) {
        DefaultPromise<V> defaultPromise = new DefaultPromise<>(this.executorService);
        defaultPromise.executorService.submit(() -> {
            try {
                defaultPromise.result = callback.apply(this.get());
            } catch (Throwable throwable) {
                defaultPromise.error = throwable;
            } finally {
                synchronized (defaultPromise) {
                    defaultPromise.flag = defaultPromise.error == null ? SUCCEED : FAILED;
                    defaultPromise.notifyAll();
                }
            }
        });
        return defaultPromise;
    }

    @Override
    public <V> Promise<V> fail(Function<? super Throwable, ? extends V> callback) {
        DefaultPromise<V> defaultPromise = new DefaultPromise<>(this.executorService);
        defaultPromise.executorService.submit(() -> {
            try {
                this.get();
                if (this.flag == FAILED) {
                    defaultPromise.result = callback.apply(this.error);
                }
            } catch (Throwable throwable) {
                defaultPromise.error = throwable;
            } finally {
                synchronized (defaultPromise) {
                    defaultPromise.flag = defaultPromise.error == null ? SUCCEED : FAILED;
                    defaultPromise.notifyAll();
                }
            }
        });
        return defaultPromise;
    }


    @Override
    public <V> Promise<V> then(Function<? super T, ? extends V> successCallback,
                               Function<? super Throwable, ? extends V> failCallback) {
        DefaultPromise<V> defaultPromise = new DefaultPromise<>(this.executorService);
        defaultPromise.executorService.submit(() -> {
            try {
                T result = this.get();
                if (this.flag == SUCCEED) {
                    defaultPromise.result = successCallback.apply(result);
                } else {
                    defaultPromise.result = failCallback.apply(this.error);
                }
            } catch (Throwable throwable) {
                defaultPromise.error = throwable;
            } finally {
                synchronized (defaultPromise) {
                    defaultPromise.flag = defaultPromise.error == null ? SUCCEED : FAILED;
                    defaultPromise.notifyAll();
                }
            }
        });
        return defaultPromise;
    }

    public static <T> Promise<T> empty() {
        DefaultPromise<T> defaultPromise = new DefaultPromise<>();
        defaultPromise.flag = defaultPromise.SUCCEED;
        return defaultPromise;
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
        // TODO: 2022/4/30 执行函数
        return null;
    }
}
