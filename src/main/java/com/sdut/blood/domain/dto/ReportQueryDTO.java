package com.sdut.blood.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 报表查询入参
 */
@Data
public class ReportQueryDTO {

    @NotBlank(message = "请选择统计维度")
    private String dimension;

    @NotNull(message = "请选择开始日期")
    private LocalDate startDate;

    @NotNull(message = "请选择结束日期")
    private LocalDate endDate;
}