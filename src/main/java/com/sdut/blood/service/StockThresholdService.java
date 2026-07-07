package com.sdut.blood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdut.blood.domain.entity.StockThreshold;

public interface StockThresholdService extends IService<StockThreshold> {
    /**
     * 根据血型查询安全阈值
     */
    Integer getThresholdByType(String bloodType);

    /**
     * 设置库存阈值
     */
    void setThreshold(String bloodType, Integer value);
}