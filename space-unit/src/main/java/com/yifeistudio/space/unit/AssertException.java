package com.yifeistudio.space.unit;

import com.yifeistudio.space.unit.model.Result;

/**
 * 断言异常
 * <p>
 * # 默认不进行堆栈追踪
 * @author : hongyi
 * created at 2022/4/19 - 10:14
 **/
public final class AssertException extends SpaceException {

    private final boolean isTraceable;

    private static final int RESULT_CODE = 400;

    public AssertException(String msg) {
        this(msg, false);
    }

    public AssertException(String msg, boolean isTraceable) {
        super(Result.fail(RESULT_CODE, msg), isTraceable);
        this.isTraceable = isTraceable;
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        if (!isTraceable) {
            return super.getStackTrace();
        }
        return null;
    }
}
///~