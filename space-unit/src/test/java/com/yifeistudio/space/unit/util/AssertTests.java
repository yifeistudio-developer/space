package com.yifeistudio.space.unit.util;

import org.junit.jupiter.api.Test;

/**
 * 断言测试
 *
 * @author : hongyi
 * created at 2022/4/15 - 13:35
 **/
class AssertTests {

    @Test
    void loadEnv() {

    }

    @Test
    void assertTrueTest() {
        Asserts.isTrue(true, "no error");
        // Asserts.isTrue(1 == 2, "assert failed");
        Asserts.isTrue(true, true, "no error");
    }

}
