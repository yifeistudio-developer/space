package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.Result;
import org.junit.jupiter.api.Test;

/**
 * Json 工具测试
 *
 * @author : hongyi
 * created at 2022/4/15 - 17:31
 **/
class JsonTests {

    @Test
    void loadEnv() {

    }

    /**
     * 序列化测试
     */
    @Test
    void stringifyTest() {
        String value = Jsons.stringify(Result.success(null)).get();
        System.out.println(value);
    }

    /**
     * 反序列化测试
     */
    @Test
    void parseTest() {

    }
}
