package com.sdut.blood.common.constants;

/**
 * 预约业务相关常量
 * 包含预约状态、取消规则等核心业务字典
 */
public class AppointmentConstants {

    // ==================== 预约状态 ====================
    /** 待参加（未到活动时间） */
    public static final String STATUS_PENDING = "待参加";
    /** 已取消 */
    public static final String STATUS_CANCELED = "已取消";
    /** 已完成 */
    public static final String STATUS_COMPLETED = "已完成";
    /** 已失效（未到场） */
    public static final String STATUS_EXPIRED = "已失效";

    // ==================== 取消规则 ====================
    /** 可取消时间阈值：活动开始前24小时 */
    public static final Integer CANCEL_BEFORE_HOURS = 24;
}