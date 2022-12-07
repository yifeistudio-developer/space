package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.Promise;
import org.junit.jupiter.api.Test;


/**
 * Promise tests
 *
 * @author : hongyi
 * created at 2022/4/30 - 15:50
 **/
class PromiseTests {

    @Test
    void promiseTest() {
        Promise<Throwable> reject = Promise.empty().reject(val -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return val;
        });

    }

}
