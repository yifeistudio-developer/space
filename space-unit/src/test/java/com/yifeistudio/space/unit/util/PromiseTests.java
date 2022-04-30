package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.DefaultPromise;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

/**
 * Promise tests
 *
 * @author : hongyi
 * created at 2022/4/30 - 15:50
 **/
class PromiseTests {

    @Test
    void promiseTest() throws ExecutionException, InterruptedException {


        DefaultPromise.empty().fail(val -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            return val;
        }).get();

    }

}
