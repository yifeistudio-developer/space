package com.yifeistudio.space.unit.model;

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

    AtomicReference<ExecutorService> _EXECUTOR = new AtomicReference<>();

    static void setExecutorService(ExecutorService executorService) {
        _EXECUTOR.set(executorService);
    }

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
