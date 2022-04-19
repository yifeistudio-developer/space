package com.yifeistudio.space.unit;
/**
 * 断言异常
 *
 * @author : hongyi
 * created at 2022/4/19 - 10:14
 **/
public class AssertException extends SpaceException{

    private static final int RESULT_CODE = 400;

    public AssertException(String msg) {
        super(Result.fail(RESULT_CODE, msg), false);
    }

}
///~