package com.sdut.blood.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 新增采血记录入参
 */
@Data
public class CollectionAddDTO {

    private Long donorId;

    private String idCard;

    @NotNull(message = "请填写献血量")
    private Integer donateAmount;

    @NotBlank(message = "请选择献血类型")
    private String donateType;

    @NotBlank(message = "请选择初筛结果")
    private String initialScreenResult;
}