package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.blood.domain.entity.BloodActivity;
import java.util.List;

/**
 * 献血活动 Mapper
 */
public interface BloodActivityMapper extends BaseMapper<BloodActivity> {

    /**
     * 查询所有可预约的活动（未开始状态）
     */
    List<BloodActivity> selectAvailableActivityList();
}