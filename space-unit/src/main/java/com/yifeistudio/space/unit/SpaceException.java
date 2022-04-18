package com.yifeistudio.space.unit;

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
        super(result.getMsg());
        this.result = result;
        this.isFatal = isFatal;
    }

    public boolean isFatal() {
        return isFatal;
    }

    public Result<?> getResult() {
        return result;
    }
}
