package com.yifeistudio.space.unit.model;

import com.yifeistudio.space.unit.util.Results;

import java.io.Serializable;

/**
 * 统一处理结果模型
 * <p>
 * * 框架层数据模型不建议业务感知
 *
 * @author : hongyi
 * created at 2022/4/15 - 13:33
 **/
public record Result<T>(int code, String msg, T data) implements Serializable {

    public Result(int code, String msg) {
        this(code, msg, null);
    }

    public Result(int code, T data) {
        this(code, null, data);
    }

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(Results.CODE_OK, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg);
    }

}
///~