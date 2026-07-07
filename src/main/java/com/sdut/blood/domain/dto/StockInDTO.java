package com.sdut.blood.domain.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 血液入库入参
 */
@Data
public class StockInDTO {

    @NotNull(message = "请选择待入库的血液记录")
    private Long collectionId;

    @NotNull(message = "请填写血液有效期")
    private LocalDate expireDate;
}