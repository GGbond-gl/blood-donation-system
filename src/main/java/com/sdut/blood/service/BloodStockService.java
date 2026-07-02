package com.sdut.blood.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdut.blood.domain.dto.StockInDTO;
import com.sdut.blood.domain.entity.BloodStock;
import com.sdut.blood.domain.vo.BloodStockVO;
import java.util.List;

/**
 * 血液库存服务接口
 */
public interface BloodStockService extends IService<BloodStock> {

    /**
     * 登记血液入库（UC32）
     */
    void stockIn(StockInDTO dto);

    /**
     * 按血型查询库存汇总
     */
    BloodStockVO getStockSummary(String bloodType);

    /**
     * 查询临期库存列表
     */
    List<BloodStock> listNearExpire(Integer days);

    /**
     * 检查库存是否低于阈值预警
     */
    boolean checkStockWarning(String bloodType);
}