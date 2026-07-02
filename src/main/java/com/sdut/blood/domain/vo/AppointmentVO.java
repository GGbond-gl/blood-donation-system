package com.sdut.blood.domain.vo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 预约记录视图（组装活动信息）
 */
@Data
public class AppointmentVO {

    private Long appointmentId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 活动日期
     */
    private LocalDate activityDate;

    /**
     * 预约时段
     */
    private String timeSlot;

    /**
     * 预约状态
     */
    private String status;

    /**
     * 预约时间
     */
    private LocalDateTime appointmentTime;
}