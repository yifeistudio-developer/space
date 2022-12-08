package com.yifeistudio.space.unit.util;

import java.util.concurrent.*;

/**
 * 资源管理
 *
 * @author : hongyi
 * created at 2022/12/8 - 19:34
 **/
public final class Resources {

    // 单例模式
    private static volatile ExecutorService _EXECUTOR = null;

    private Resources() {

    }

    /**
     * 获取线程池执行器
     *
     * @return 线程池执行器
     */
    public static ExecutorService getExecutorService() {
        if (_EXECUTOR != null) {
            return _EXECUTOR;
        }
        synchronized (Resources.class) {
            if (_EXECUTOR != null) {
                return _EXECUTOR;
            }
            int processorNum = Runtime.getRuntime().availableProcessors();
            int coreSize = processorNum + 1;
            BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
            _EXECUTOR = new ThreadPoolExecutor(coreSize,
                    2 * coreSize,
                    5,
                    TimeUnit.MINUTES,
                    blockingQueue, r -> {
                Thread thread = new Thread(r);
                thread.setName("default-promise-thread-" + thread.getId());
                return thread;
            });
            // TODO: 2022/12/8 register jvm shutdown hook

        }

        return _EXECUTOR;
    }























}
