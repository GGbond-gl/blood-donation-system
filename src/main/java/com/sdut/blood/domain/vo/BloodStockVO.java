package com.sdut.blood.domain.vo;

import lombok.Data;
import java.time.LocalDate;

/**
 * 库存视图
 */
@Data
public class BloodStockVO {

    private Long id;

    private String bloodType;

    /**
     * 当前库存总量（ml）
     */
    private Integer totalAmount;

    /**
     * 是否临期
     */
    private Boolean nearExpire;

    /**
     * 有效期
     */
    private LocalDate expireDate;

    /**
     * 可用天数
     */
    private Long availableDays;

    /**
     * 库存状态
     */
    private String status;
}