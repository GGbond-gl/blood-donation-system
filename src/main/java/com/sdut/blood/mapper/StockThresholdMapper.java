package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.blood.domain.entity.StockThreshold;

/**
 * 库存阈值 Mapper
 */
public interface StockThresholdMapper extends BaseMapper<StockThreshold> {

    /**
     * 根据血型查询阈值配置
     */
    StockThreshold selectByBloodType(String bloodType);
}