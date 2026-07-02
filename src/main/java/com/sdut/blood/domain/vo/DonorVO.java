package com.sdut.blood.domain.vo;

import lombok.Data;
import java.time.LocalDate;

/**
 * 献血者档案视图（身份证号脱敏）
 */
@Data
public class DonorVO {

    private Long id;

    private String name;

    /**
     * 脱敏后的身份证号
     */
    private String idCardMask;

    private String bloodType;

    private String phone;

    private String donorStatus;

    private LocalDate lastDonateDate;
}