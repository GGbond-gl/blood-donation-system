package com.sdut.blood.domain.vo;

import lombok.Data;

/**
 * 登录成功返回对象
 */
@Data
public class LoginVO {

    /**
     * JWT令牌
     */
    private String token;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 角色标识
     */
    private String role;
}