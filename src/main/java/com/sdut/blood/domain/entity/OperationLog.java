package com.sdut.blood.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 操作日志表
 */
@Data
@TableName("operation_log")
public class OperationLog {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 操作人ID
     */
    private Long operatorId;

    /**
     * 操作人姓名
     */
    private String operatorName;

    /**
     * 操作类型：新增/修改/删除/导出等
     */
    private String operationType;

    /**
     * 操作内容详情
     */
    private String operationContent;

    /**
     * 操作时间
     */
    private LocalDateTime operationTime;

    /**
     * 操作IP地址
     */
    private String ipAddress;
}