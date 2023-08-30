package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.Result;

/**
 * 通用处理结果定义
 * <p>
 * 统一错误编码
 * [200, 300) - 请求视为成功
 * [300, 1000) - 系统保留错误编码
 * @author : hongyi
 * created at 2022/10/18 - 15:12
 **/
public final class Results {

    /**
     * 请求成功
     * [200, 300)
     */
    public static final int CODE_OK = 200;

    /**
     * 创建成功
     */
    public static final int CODE_CREATED = 201;

    /**
     * 重复的请求
     */
    public static final int CODE_REPORTED = 208;

    /**
     * 参数错误
     * [400, 500)
     */
    public static final int CODE_BAD_REQUEST = 400;

    /**
     * 未认证
     */
    public static final int CODE_UNAUTHORIZED = 401;

    /**
     * 未知异常
     * [500, 1000)
     */
    public static final int CODE_UNKNOWN_ERROR = 500;

    //==================================================================================================================

    /**
     * 成功
     */
    public static final Result<?> SUCCEED = Result.success();

    /**
     * 创建成功
     */
    public static final Result<?> SUCCEED_CREATED = new Result<>(CODE_CREATED, null);

    /**
     * 成功-重复的请求
     */
    public static final Result<?> SUCCEED_REPORTED = new Result<>(CODE_REPORTED, null);

    /**
     * 系统内部错误
     */
    public static final Result<?> INTERNAL_ERROR = Result.fail(CODE_UNKNOWN_ERROR, "你干嘛啊哈～ 哎呦～你好烦～");


}
