package com.yifeistudio.space.unit.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        String localDatePattern = "yyyy-MM-dd";
        String localTimePattern = "HH:mm:ss";
        String localDateTimePattern = String.format("%s %s", localDatePattern, localTimePattern);
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        LocalDateTimeDeserializer dateTimeDeserializer = new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(localDateTimePattern));
        LocalDateTimeSerializer dateTimeSerializer = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(localDateTimePattern));
        LocalDateDeserializer dateDeserializer = new LocalDateDeserializer(DateTimeFormatter.ofPattern(localDatePattern));
        LocalDateSerializer dateSerializer = new LocalDateSerializer(DateTimeFormatter.ofPattern(localDatePattern));
        LocalTimeDeserializer timeDeserializer = new LocalTimeDeserializer(DateTimeFormatter.ofPattern(localTimePattern));
        LocalTimeSerializer timeSerializer = new LocalTimeSerializer(DateTimeFormatter.ofPattern(localTimePattern));
        javaTimeModule.addDeserializer(LocalDateTime.class, dateTimeDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, dateTimeSerializer);
        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(javaTimeModule);

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
        Asserts.notNull(objectMapper, "objectMapper is required.");
        Jsons.objectMapper = objectMapper;
    }

}
///～