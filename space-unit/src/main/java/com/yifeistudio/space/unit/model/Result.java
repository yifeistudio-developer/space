package com.yifeistudio.space.unit.model;

import java.io.Serializable;

/**
 * 统一处理结果模型
 *
 * * 框架层数据模型不建议业务感知
 *
 * @author : hongyi
 * created at 2022/4/15 - 13:33
 **/
public class Result<T> implements Serializable {

    private int code;

    private T data;

    private String msg;

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static <T> Result<T> success() {
        return Result.success(null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(0, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg);
    }

    //---------- getter setter ----------

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
///~