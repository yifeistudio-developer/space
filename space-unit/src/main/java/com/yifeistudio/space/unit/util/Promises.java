package com.yifeistudio.space.unit.util;

import java.util.concurrent.*;

/**
 * Promise 工具类
 *
 * @author : hongyi
 * created at 2022/4/30 - 00:23
 **/
public final class Promises {

    /**
     * 执行器
     */
    private static ExecutorService executorService;

    public static synchronized void setExecutorService(ExecutorService executorService) {
        Promises.executorService = executorService;
    }

    public static synchronized ExecutorService getExecutorService() {
        if (executorService != null) {
            return executorService;
        }
        int processorNum = Runtime.getRuntime().availableProcessors();
        int coreSize = processorNum + 1;
        BlockingQueue<Runnable> blockingQueue = new LinkedBlockingQueue<>();
        Promises.executorService = new ThreadPoolExecutor(coreSize,
                2 * coreSize,
                5,
                TimeUnit.MINUTES,
                blockingQueue, r -> {
            Thread thread = new Thread(r);
            thread.setName("default-promise-thread-" + thread.getId());
            return thread;
        });
        return executorService;
    }
}
