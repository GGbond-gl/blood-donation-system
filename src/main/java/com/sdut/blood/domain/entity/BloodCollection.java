package com.sdut.blood.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 采血记录表
 */
@Data
@TableName("blood_collection")
public class BloodCollection {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 关联献血者档案ID
     */
    private Long donorId;

    /**
     * 献血者身份证号
     */
    private String donorIdCard;

    /**
     * 献血量（单位：ml/治疗量）
     */
    private Integer donateAmount;

    /**
     * 献血类型：全血/成分血
     */
    private String donateType;

    /**
     * 初筛结果：合格/不合格
     */
    private String initialScreenResult;

    /**
     * 采血时间
     */
    private LocalDateTime collectionTime;

    /**
     * 操作管理员ID
     */
    private Long operatorId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Integer deleted;
}