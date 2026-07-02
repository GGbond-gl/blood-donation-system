package com.sdut.blood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.blood.domain.entity.StockThreshold;
import com.sdut.blood.mapper.StockThresholdMapper;
import com.sdut.blood.service.StockThresholdService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class StockThresholdServiceImpl extends ServiceImpl<StockThresholdMapper, StockThreshold> implements StockThresholdService {

    @Override
    public Integer getThresholdByType(String bloodType) {
        StockThreshold threshold = baseMapper.selectByBloodType(bloodType);
        // 默认安全阈值 2000ml
        return threshold == null ? 2000 : threshold.getThresholdValue();
    }

    @Override
    public void setThreshold(String bloodType, Integer value) {
        StockThreshold exist = baseMapper.selectByBloodType(bloodType);
        if (exist == null) {
            StockThreshold threshold = new StockThreshold();
            threshold.setBloodType(bloodType);
            threshold.setThresholdValue(value);
            threshold.setUpdateTime(LocalDateTime.now());
            save(threshold);
        } else {
            exist.setThresholdValue(value);
            exist.setUpdateTime(LocalDateTime.now());
            updateById(exist);
        }
    }
}