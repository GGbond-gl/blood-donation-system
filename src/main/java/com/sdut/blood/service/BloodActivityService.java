package com.sdut.blood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdut.blood.domain.entity.BloodActivity;
import java.util.List;

/**
 * 献血活动服务接口
 */
public interface BloodActivityService extends IService<BloodActivity> {

    /**
     * 查询所有可预约的活动
     */
    List<BloodActivity> listAvailableActivities();

    /**
     * 扣减指定时段名额
     */
    boolean decreaseQuota(Long activityId, String timeSlot);

    /**
     * 恢复指定时段名额
     */
    boolean increaseQuota(Long activityId, String timeSlot);
}