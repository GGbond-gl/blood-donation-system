package com.sdut.blood.domain.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PendingStockInVO {

    private Long collectionId;

    private Long testId;

    private Long donorId;

    private String donorName;

    private String bloodType;

    private String phone;

    private Integer donateAmount;

    private String donateType;

    private String recheckResult;

    private LocalDateTime collectionTime;

    private LocalDateTime judgeTime;

    private String operatorName;
}