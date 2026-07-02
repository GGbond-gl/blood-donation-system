package com.sdut.blood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.blood.common.exception.BusinessException;
import com.sdut.blood.domain.entity.BloodActivity;
import com.sdut.blood.mapper.BloodActivityMapper;
import com.sdut.blood.service.BloodActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class BloodActivityServiceImpl extends ServiceImpl<BloodActivityMapper, BloodActivity> implements BloodActivityService {

    @Override
    public List<BloodActivity> listAvailableActivities() {
        return baseMapper.selectAvailableActivityList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean decreaseQuota(Long activityId, String timeSlot) {
        BloodActivity activity = getById(activityId);
        if (activity == null) {
            throw new BusinessException("活动不存在");
        }

        if ("上午".equals(timeSlot)) {
            if (activity.getMorningRemaining() <= 0) {
                throw new BusinessException("该时段预约人数已满，请选择其他时段");
            }
            activity.setMorningRemaining(activity.getMorningRemaining() - 1);
        } else if ("下午".equals(timeSlot)) {
            if (activity.getAfternoonRemaining() <= 0) {
                throw new BusinessException("该时段预约人数已满，请选择其他时段");
            }
            activity.setAfternoonRemaining(activity.getAfternoonRemaining() - 1);
        } else {
            throw new BusinessException("时段参数错误");
        }

        return updateById(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean increaseQuota(Long activityId, String timeSlot) {
        BloodActivity activity = getById(activityId);
        if (activity == null) {
            return false;
        }

        if ("上午".equals(timeSlot)) {
            activity.setMorningRemaining(activity.getMorningRemaining() + 1);
        } else if ("下午".equals(timeSlot)) {
            activity.setAfternoonRemaining(activity.getAfternoonRemaining() + 1);
        }

        return updateById(activity);
    }
}