package com.sdut.blood.service;

import com.sdut.blood.domain.dto.ReportQueryDTO;
import com.sdut.blood.domain.vo.ReportVO;
import javax.servlet.http.HttpServletResponse;

/**
 * 报表统计服务接口
 */
public interface ReportService {

    /**
     * 生成统计结果
     */
    ReportVO generateReport(ReportQueryDTO dto);

    /**
     * 导出Excel报表（UC40）
     */
    void exportExcel(ReportQueryDTO dto, HttpServletResponse response);
}