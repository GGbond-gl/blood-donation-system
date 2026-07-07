package com.sdut.blood.common.exception;

import com.sdut.blood.common.result.Result;
import com.sdut.blood.common.result.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * 全局异常处理器
 * 统一捕获所有Controller层异常，封装为标准Result返回，避免暴露堆栈信息
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获自定义业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        log.warn("业务异常：{}", e.getMsg());
        return Result.error(e.getCode(), e.getMsg());
    }

    /**
     * 捕获@RequestBody参数校验异常
     * 对应DTO中@NotBlank、@NotNull等注解校验失败的场景
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        log.warn("参数校验异常：{}", errorMsg);
        return Result.error(ResultCode.PARAM_ERROR.getCode(), errorMsg);
    }

    /**
     * 捕获表单参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    public Result<Void> handleBindException(BindException e) {
        String errorMsg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("；"));
        log.warn("参数绑定异常：{}", errorMsg);
        return Result.error(ResultCode.PARAM_ERROR.getCode(), errorMsg);
    }

    /**
     * 捕获所有未处理的系统级异常
     * 打印错误日志，返回通用错误提示，避免泄露系统细节
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        log.error("系统异常：", e);
        return Result.error(ResultCode.SYSTEM_ERROR);
    }
}