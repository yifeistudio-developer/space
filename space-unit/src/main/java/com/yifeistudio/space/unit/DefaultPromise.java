package com.yifeistudio.space.unit;

import com.yifeistudio.space.unit.util.Asserts;
import com.yifeistudio.space.unit.util.Resources;

import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/**
 * 默认实现
 *
 * @author : hongyi
 * created at 2022/4/29 - 19:41
 **/
public class DefaultPromise<T> implements Promise<T> {

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
        this((byte) 0);
    }

    public DefaultPromise(byte flag) {
        this(flag, null);
    }

    public DefaultPromise(byte flag, Throwable t) {
        this(flag, t, Resources.getExecutorService());
    }

    public DefaultPromise(byte flag, Throwable t, ExecutorService executorService) {
        Asserts.notNull(executorService, "executorService is required nonNull");
        this.flag = flag;
        this.error = t;
        this.executorService = executorService;
    }

    @Override
    public <V> Promise<V> then(Function<? super T, ? extends V> successCallback,
                               Function<? super Throwable, ? extends V> failCallback) {
        DefaultPromise<V> next = new DefaultPromise<>((byte) 0, null, this.executorService);
        this.executorService.submit(() -> {
            try {
                T result = this.get();
                if (this.flag == SUCCEED && successCallback != null) {
                    next.result = successCallback.apply(result);
                    return;
                }
                if (this.flag == FAILED && failCallback != null) {
                    next.result = failCallback.apply(this.error);
                }
            } catch (Throwable t) {
                next.error = t;
            } finally {
                synchronized (next) {
                    next.flag = next.error == null ? SUCCEED : FAILED;
                    next.notifyAll();
                }
            }
        });
        return next;
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

}
