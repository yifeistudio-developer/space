package com.yifeistudio.space.unit;

import com.yifeistudio.space.unit.model.Result;

/**
 * 应用异常
 *
 * @author : hongyi
 * created at 2022/4/15 - 13:31
 **/
public class SpaceException extends RuntimeException {

    private final boolean isFatal;

    private final Result<?> result;

    public SpaceException(Result<?> result) {
        this(result, false);
    }

    public SpaceException(Result<?> result, boolean isFatal) {
        this(result, isFatal, null);
    }

    public SpaceException(Result<?> result, boolean isFatal, Throwable throwable) {
        super(result.msg(), throwable);
        this.result = result;
        this.isFatal = isFatal;
    }

    //---------- getter setter ----------
    public boolean isFatal() {
        return isFatal;
    }

    public Result<?> getResult() {
        return result;
    }
}
///~