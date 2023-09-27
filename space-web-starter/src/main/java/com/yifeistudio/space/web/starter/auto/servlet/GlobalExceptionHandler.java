package com.yifeistudio.space.web.starter.auto.servlet;

import com.yifeistudio.space.unit.SpaceException;
import com.yifeistudio.space.unit.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

/**
 * 文件描述
 *
 * @author yi
 * <p>
 * Created at 2023/9/26 下午6:11
 * @since 1.0
 */
@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 404
     *
     * @return 404
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public Result<?> handleHandlerFoundException() {
        return Result.fail(HttpStatus.NOT_FOUND.value(), "接口不存在");
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(SpaceException.class)
    public Result<?> handleAssertException(SpaceException ex) {
        return ex.getResult();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        String paramType = ex.getParameterType();
        String msg = String.format("缺少必填参数：%s[%s]", paramName, paramType);
        return Result.fail(HttpStatus.BAD_REQUEST.value(), msg);
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String requestMethod = ex.getMethod();
        String expectedMethod = Arrays.toString(ex.getSupportedMethods());
        String msg = String.format("不支持的请求方式：%s，请使用 %s", requestMethod, expectedMethod);
        return Result.fail(HttpStatus.METHOD_NOT_ALLOWED.value(), msg);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        HttpInputMessage httpInputMessage = ex.getHttpInputMessage();
//        try {
//            String inputStr = Resources.read(httpInputMessage.getBody());
//            log.error("catch bad stream: {}", inputStr);
//            return Result.fail(HttpStatus.BAD_REQUEST.value(), "无法读取请求内容");
//        } catch (IOException e) {
//            return handleUnknownException(e);
//        }
        return Result.fail(HttpStatus.BAD_REQUEST.value(), "读取请求体失败");
    }

    /**
     * 不支持的媒体类型
     *
     * @param ex 异常信息
     * @return 异常提示
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
        MediaType contentType = ex.getContentType();
        HttpStatusCode statusCode = ex.getStatusCode();
        return Result.fail(statusCode.value(), "不支持的媒体类型：" + contentType);
    }

    /**
     * 未知异常
     *
     * @param ex 异常
     * @return 未知错误提示
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public Result<?> handleUnknownException(Throwable ex) {
        log.error("catch unexpected error: ", ex);
        return Result.fail(HttpStatus.INTERNAL_SERVER_ERROR.value(), "这就尴尬了，被你发现了。。。");
    }
}
///~