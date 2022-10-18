package com.yifeistudio.space.unit.util;

import com.yifeistudio.space.unit.model.Result;

/**
 * 通用处理结果定义
 *
 * @author : hongyi
 * created at 2022/10/18 - 15:12
 **/
public final class Results {

    /**
     * 成功
     */
    public static final Result<?> SUCCEED = Result.success();

    /**
     * 成功-重复的请求
     */
    public static final Result<?> SUCCEED_DUPLICATE = new Result<>(201, null);

    /**
     * 系统内部错误
     */
    public static final Result<?> INTERNAL_ERROR = Result.fail(500, "系统有点儿小问题，请稍后重试～");



}
