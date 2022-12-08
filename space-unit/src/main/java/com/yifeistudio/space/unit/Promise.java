package com.yifeistudio.space.unit;

import com.yifeistudio.space.unit.util.Asserts;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

/**
 * Promise
 *
 * @author : hongyi
 * created at 2022/4/29 - 12:49
 **/
public interface Promise<T> {

    /**
     * 执行成功
     */
    byte SUCCEED = 1;

    /**
     * 执行失败
     */
    byte FAILED = -1;

    /**
     * 执行器
     */
    AtomicReference<ExecutorService> _EXECUTOR = new AtomicReference<>();

    /**
     * 配置执行器
     *
     * @param executorService 执行器
     */
    static void setExecutorService(ExecutorService executorService) {
        Asserts.notNull(executorService, "executor service must not be null.");
        _EXECUTOR.set(executorService);
    }

    /**
     * 获取执行器
     *
     * @return 执行器
     */
    static ExecutorService getExecutorService() {
        ExecutorService executorService = _EXECUTOR.get();
        if (executorService != null) {
            return executorService;
        }
        int processorNum = Runtime.getRuntime().availableProcessors();
        int coreSize = processorNum + 1;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        executorService = new ThreadPoolExecutor(coreSize,
                2 * coreSize,
                5,
                TimeUnit.MINUTES,
                blockingQueue, r -> {
            Thread thread = new Thread(r);
            thread.setName("default-promise-thread-" + thread.getId());
            return thread;
        });
        return _EXECUTOR.compareAndSet(null, executorService) ? executorService : _EXECUTOR.get();
    }

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
        return new DefaultPromise<>(Promise.SUCCEED);
    }

    static <V> Promise<V> resolve(V value) {
        return empty().resolve(val -> value);
    }

    static <V> Promise<V> reject(Throwable t) {
        return new DefaultPromise<>(Promise.FAILED, t);
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
