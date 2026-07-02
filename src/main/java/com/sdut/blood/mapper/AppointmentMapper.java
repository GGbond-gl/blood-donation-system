package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.blood.domain.entity.Appointment;
import com.sdut.blood.domain.vo.AppointmentVO;

/**
 * 预约记录 Mapper
 */
public interface AppointmentMapper extends BaseMapper<Appointment> {

    /**
     * 分页查询用户预约列表（关联活动信息）
     */
    IPage<AppointmentVO> selectUserAppointmentPage(Page<AppointmentVO> page, Long userId);

    /**
     * 统计用户是否已预约某活动（重复预约校验）
     */
    Integer countByUserIdAndActivityId(Long userId, Long activityId);
}