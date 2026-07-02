package com.sdut.blood.domain.vo;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * 报表统计视图
 */
@Data
public class ReportVO {

    /**
     * 统计维度
     */
    private String dimension;

    /**
     * 统计时间范围
     */
    private String dateRange;

    /**
     * 统计明细列表
     */
    private List<Map<String, Object>> dataList;

    /**
     * 图表所需X轴数据
     */
    private List<String> xAxis;

    /**
     * 图表所需Y轴数据
     */
    private List<Integer> yAxis;

    /**
     * 合计值
     */
    private Integer total;
}