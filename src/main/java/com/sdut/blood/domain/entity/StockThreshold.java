package com.sdut.blood.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 库存阈值配置表
 */
@Data
@TableName("stock_threshold")
public class StockThreshold {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 血型
     */
    private String bloodType;

    /**
     * 安全库存阈值（ml）
     */
    private Integer thresholdValue;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人ID
     */
    private Long updateBy;
}