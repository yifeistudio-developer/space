package com.yifeistudio.space.unit.model;

/**
 * 通用常量定义
 *
 * @author yi
 * <p>
 * Created at 2023/9/7 下午5:54
 * @since 1.0
 */
public final class Constants {

    // yes or no.
    public static byte YES = 1;
    public static byte NO = 0;

    // success or failed.
    public static byte SUCCEED = 1;
    public static byte FAILED = -1;


    // 低于该阈值视为http保留错误编码，将改变响应头编码；高于该阈值则视为业务编码响应头统一为 2xx
    public static int HTTP_ERROR_THRESHOLD = 1024;

}
