package com.sdut.blood.common.exception;

import com.sdut.blood.common.result.ResultCode;
import lombok.Getter;

/**
 * 自定义业务异常
 * 业务逻辑校验不通过时抛出，如"预约名额已满""身份证已存在"等场景
 */
@Getter
public class BusinessException extends RuntimeException {

    /**
     * 异常状态码
     */
    private final Integer code;

    /**
     * 异常提示信息
     */
    private final String msg;

    /**
     * 仅传入异常信息，默认业务异常码
     */
    public BusinessException(String msg) {
        super(msg);
        this.code = ResultCode.BUSINESS_ERROR.getCode();
        this.msg = msg;
    }

    /**
     * 传入状态码和异常信息
     */
    public BusinessException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    /**
     * 使用状态码枚举
     */
    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
        this.msg = resultCode.getMsg();
    }
}