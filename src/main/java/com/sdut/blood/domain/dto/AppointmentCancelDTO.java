package com.sdut.blood.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotNull;

/**
 * 取消预约入参
 */
@Data
public class AppointmentCancelDTO {

    @NotNull(message = "请选择要取消的预约记录")
    private Long appointmentId;
}