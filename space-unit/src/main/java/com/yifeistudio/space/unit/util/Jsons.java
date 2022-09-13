package com.yifeistudio.space.unit.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

/**
 * Json(Jackson) 工具
 * <p>
 * 封装Jackson-Json 序列化/反序列化方法
 * 忽略异常
 *
 * @author : hongyi
 * created at 2022/4/15 - 16:45
 **/
public final class Jsons {

    private static ObjectMapper objectMapper;

    // 默认配置
    static {
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private Jsons() {}

    /**
     * Json 序列化
     *
     * @param obj 序列化对象
     * @return Json 字符串
     */
    public static Optional<String> stringify(Object obj) {
        return stringify(obj, false);
    }

    /**
     * Json 序列化
     *
     * @param obj    序列化对象
     * @param format 格式化
     * @return Json 字符串（格式化）
     */
    public static Optional<String> stringify(Object obj, boolean format) {
        try {
            String value;
            if (format) {
                value = objectMapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(obj);
            } else {
                value = objectMapper.writeValueAsString(obj);
            }
            return Optional.of(value);
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    /**
     * Json 字符串反序列化
     *
     * @param json json-字符串
     * @param clz  目标类型
     * @return 目标类型对象
     * @param <T> 目标类型
     */
    public static <T> Optional<T> parse(String json, Class<T> clz) {
        try {
            T value = objectMapper.readValue(json, clz);
            return Optional.of(value);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    //---------- getter setter ----------

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public static void setObjectMapper(ObjectMapper objectMapper) {
        Jsons.objectMapper = objectMapper;
    }

}
///～