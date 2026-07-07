package com.sdut.blood.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 提交预约入参
 */
@Data
public class AppointmentSubmitDTO {

    @NotNull(message = "请选择预约活动")
    private Long activityId;

    @NotBlank(message = "请选择预约时段")
    private String timeSlot;
}