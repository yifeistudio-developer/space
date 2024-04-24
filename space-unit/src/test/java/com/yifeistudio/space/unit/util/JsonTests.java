package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.Result;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        Optional<String> optionalResult = Jsons.stringify(Result.success(LocalDateTime.now()));
        if (optionalResult.isPresent()) {
            String value = optionalResult.get();
            System.out.println(value);
        }

    }

    /**
     * 反序列化测试
     */
    @Test
    void parseTest() {
        String str = "[\"ssss\",\"ssss\",\"ssss\"]";
        List<String> result = Jsons.parseListAndGet(str, String.class);
        System.out.println(result);
    }
}
