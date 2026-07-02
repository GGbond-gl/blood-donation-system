package com.sdut.blood.service.impl;

import com.alibaba.excel.EasyExcel;
import com.sdut.blood.common.exception.BusinessException;
import com.sdut.blood.domain.dto.ReportQueryDTO;
import com.sdut.blood.domain.vo.ReportVO;
import com.sdut.blood.service.ReportService;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Override
    public ReportVO generateReport(ReportQueryDTO dto) {
        ReportVO vo = new ReportVO();
        vo.setDimension(dto.getDimension());
        vo.setDateRange(dto.getStartDate() + " 至 " + dto.getEndDate());

        // 模拟统计逻辑，实际项目需关联数据库查询
        List<Map<String, Object>> dataList = new ArrayList<>();
        List<String> xAxis = new ArrayList<>();
        List<Integer> yAxis = new ArrayList<>();
        int total = 0;

        // 按维度生成模拟数据（实际开发替换为SQL统计）
        for (int i = 1; i <= 6; i++) {
            Map<String, Object> item = new HashMap<>();
            item.put("month", i + "月");
            int value = (int) (Math.random() * 500 + 100);
            item.put("value", value);
            dataList.add(item);
            xAxis.add(i + "月");
            yAxis.add(value);
            total += value;
        }

        vo.setDataList(dataList);
        vo.setXAxis(xAxis);
        vo.setYAxis(yAxis);
        vo.setTotal(total);
        return vo;
    }

    @Override
    public void exportExcel(ReportQueryDTO dto, HttpServletResponse response) {
        ReportVO report = generateReport(dto);

        // 数据量校验
        if (report.getDataList().size() > 10000) {
            throw new BusinessException("导出数据量过大，请缩小时间范围后重试");
        }

        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("献血统计报表", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 导出Excel
            EasyExcel.write(response.getOutputStream())
                    .head(generateExcelHead(dto.getDimension()))
                    .sheet("统计数据")
                    .doWrite(report.getDataList());

        } catch (IOException e) {
            throw new BusinessException("报表生成失败，请稍后重试");
        }
    }

    /**
     * 生成Excel表头
     */
    private List<List<String>> generateExcelHead(String dimension) {
        List<List<String>> head = new ArrayList<>();
        List<String> head0 = new ArrayList<>();
        head0.add("月份");
        List<String> head1 = new ArrayList<>();
        head1.add(dimension);
        head.add(head0);
        head.add(head1);
        return head;
    }
}