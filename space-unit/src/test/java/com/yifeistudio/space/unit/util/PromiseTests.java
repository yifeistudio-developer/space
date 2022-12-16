package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.Promise;
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
        String valx = Promise.<String>empty().resolve(val -> {
            try {
                Thread.sleep(3000);
                System.out.println("我执行了3秒");
                throw new RuntimeException("我报错了");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).resolve(val -> {
            try {
                Thread.sleep(4000);
                System.out.println("我执行了4秒");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "";
        }).get();



    }

}
