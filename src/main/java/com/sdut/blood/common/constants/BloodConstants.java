package com.sdut.blood.common.constants;

/**
 * 血液业务相关常量
 * 包含血型、献血类型、血液状态、献血量规范等核心业务字典
 */
public class BloodConstants {

    // ==================== 血型枚举 ====================
    public enum BloodType {
        A("A型"),
        B("B型"),
        O("O型"),
        AB("AB型");

        private final String desc;

        BloodType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
    }

    // ==================== 献血类型 ====================
    /** 全血 */
    public static final String DONATE_TYPE_WHOLE = "全血";
    /** 成分血（单采血小板） */
    public static final String DONATE_TYPE_COMPONENT = "成分血";

    // ==================== 血液状态 ====================
    /** 待检验 */
    public static final String STATUS_PENDING_TEST = "待检验";
    /** 检验合格 */
    public static final String STATUS_QUALIFIED = "合格";
    /** 检验不合格 */
    public static final String STATUS_UNQUALIFIED = "不合格";
    /** 已入库 */
    public static final String STATUS_STORED = "已入库";
    /** 待销毁 */
    public static final String STATUS_TO_DESTROY = "待销毁";

    // ==================== 献血量规范 ====================
    /** 全血单次最小献血量 */
    public static final Integer WHOLE_BLOOD_MIN = 200;
    /** 全血单次最大献血量 */
    public static final Integer WHOLE_BLOOD_MAX = 400;
    /** 成分血最小治疗量 */
    public static final Integer COMPONENT_BLOOD_MIN = 1;
    /** 成分血最大治疗量 */
    public static final Integer COMPONENT_BLOOD_MAX = 2;
}