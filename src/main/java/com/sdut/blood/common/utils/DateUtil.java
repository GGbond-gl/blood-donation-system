package com.sdut.blood.common.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 日期计算工具类
 * 适配献血间隔校验、预约取消时间校验等业务场景
 */
public class DateUtil {

    /**
     * 计算两个日期之间的天数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 间隔天数
     */
    public static long daysBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    /**
     * 计算两个时间之间的小时差
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 间隔小时数
     */
    public static long hoursBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return ChronoUnit.HOURS.between(startTime, endTime);
    }

    /**
     * 计算两个日期之间的月数差
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 间隔月数
     */
    public static long monthsBetween(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.MONTHS.between(startDate, endDate);
    }

    /**
     * 校验全血献血间隔是否满足（6个月）
     *
     * @param lastDonateDate 上次献血日期
     * @return true-满足间隔要求 false-不满足
     */
    public static boolean checkWholeBloodInterval(LocalDate lastDonateDate) {
        if (lastDonateDate == null) {
            // 无历史献血记录，默认满足
            return true;
        }
        long months = monthsBetween(lastDonateDate, LocalDate.now());
        return months >= 6;
    }

    /**
     * 校验成分血献血间隔是否满足（28天）
     *
     * @param lastDonateDate 上次献血日期
     * @return true-满足间隔要求 false-不满足
     */
    public static boolean checkComponentBloodInterval(LocalDate lastDonateDate) {
        if (lastDonateDate == null) {
            return true;
        }
        long days = daysBetween(lastDonateDate, LocalDate.now());
        return days >= 28;
    }

    /**
     * 校验预约是否可取消（活动开始前24小时以上可取消）
     *
     * @param activityStartTime 活动开始时间
     * @return true-可取消 false-不可取消
     */
    public static boolean canCancelAppointment(LocalDateTime activityStartTime) {
        long hours = hoursBetween(LocalDateTime.now(), activityStartTime);
        return hours >= 24;
    }
}