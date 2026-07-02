package com.sdut.blood.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sdut.blood.common.exception.BusinessException;
import com.sdut.blood.domain.dto.StockInDTO;
import com.sdut.blood.domain.entity.BloodCollection;
import com.sdut.blood.domain.entity.BloodStock;
import com.sdut.blood.domain.entity.BloodTest;
import com.sdut.blood.domain.entity.Donor;
import com.sdut.blood.domain.vo.BloodStockVO;
import com.sdut.blood.mapper.BloodStockMapper;
import com.sdut.blood.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
public class BloodStockServiceImpl extends ServiceImpl<BloodStockMapper, BloodStock> implements BloodStockService {

    @Resource
    private BloodCollectionService bloodCollectionService;

    @Resource
    private BloodTestService bloodTestService;

    @Resource
    private DonorService donorService;

    @Resource
    private StockThresholdService stockThresholdService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void stockIn(StockInDTO dto) {
        // 1. 校验采血记录存在
        BloodCollection collection = bloodCollectionService.getById(dto.getCollectionId());
        if (collection == null) {
            throw new BusinessException("采血记录不存在");
        }

        // 2. 校验血液状态为合格
        BloodTest test = bloodTestService.getByCollectionId(dto.getCollectionId());
        if (test == null || !"合格".equals(test.getBloodStatus())) {
            throw new BusinessException("不合格血液无法入库，请核对后操作");
        }

        // 3. 校验有效期有效
        if (dto.getExpireDate().isBefore(LocalDate.now())) {
            throw new BusinessException("有效期无效，请填写正确日期");
        }

        // 4. 校验是否已入库
        BloodStock existStock = lambdaQuery()
                .eq(BloodStock::getCollectionId, dto.getCollectionId())
                .one();
        if (existStock != null) {
            throw new BusinessException("该血液已入库，请勿重复操作");
        }

        // 5. 获取血型
        Donor donor = donorService.getById(collection.getDonorId());

        // 6. 生成入库记录
        BloodStock stock = new BloodStock();
        stock.setCollectionId(dto.getCollectionId());
        stock.setBloodType(donor.getBloodType());
        stock.setBloodAmount(collection.getDonateAmount());
        stock.setExpireDate(dto.getExpireDate());
        stock.setStatus("正常");
        save(stock);

        // 7. 更新检验记录状态为已入库
        test.setBloodStatus("已入库");
        bloodTestService.updateById(test);
    }

    @Override
    public BloodStockVO getStockSummary(String bloodType) {
        Map<String, Object> map = baseMapper.selectStockTotalByBloodType(bloodType);
        BloodStockVO vo = new BloodStockVO();
        vo.setBloodType(bloodType);
        vo.setTotalAmount(map == null ? 0 : ((Number) map.get("total_amount")).intValue());
        vo.setStatus("正常");

        // 计算临期状态
        List<BloodStock> nearList = listNearExpire(7);
        vo.setNearExpire(!nearList.isEmpty());

        // 计算可用天数（简化计算）
        long days = ChronoUnit.DAYS.between(LocalDate.now(), LocalDate.now().plusDays(30));
        vo.setAvailableDays(days);
        return vo;
    }

    @Override
    public List<BloodStock> listNearExpire(Integer days) {
        return baseMapper.selectNearExpireList(days);
    }

    @Override
    public boolean checkStockWarning(String bloodType) {
        BloodStockVO summary = getStockSummary(bloodType);
        Integer threshold = stockThresholdService.getThresholdByType(bloodType);
        return summary.getTotalAmount() < threshold;
    }
}