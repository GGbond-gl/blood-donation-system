package com.sdut.blood.common.result;

import lombok.Getter;

/**
 * 接口响应状态码枚举
 * 统一管理所有业务状态码，避免硬编码
 */
@Getter
public enum ResultCode {

    // 通用状态
    SUCCESS(200, "操作成功"),
    PARAM_ERROR(400, "请求参数错误"),
    UNAUTHORIZED(401, "未登录或登录已过期，请重新登录"),
    PERMISSION_DENIED(403, "权限不足，无法执行该操作"),

    // 业务异常（默认500）
    BUSINESS_ERROR(500, "业务处理异常"),
    SYSTEM_ERROR(500, "系统内部错误，请稍后重试");

    private final Integer code;
    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}