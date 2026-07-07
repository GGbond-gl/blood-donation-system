package com.sdut.blood.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 预约记录表
 */
@Data
@TableName("appointment")
public class Appointment {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 献血者用户ID
     */
    private Long userId;

    /**
     * 关联活动ID
     */
    private Long activityId;

    /**
     * 预约时段：上午/下午
     */
    private String timeSlot;

    /**
     * 预约状态：待参加/已取消/已完成/已失效
     */
    private String status;

    /**
     * 预约提交时间
     */
    private LocalDateTime appointmentTime;

    /**
     * 取消时间
     */
    private LocalDateTime cancelTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}