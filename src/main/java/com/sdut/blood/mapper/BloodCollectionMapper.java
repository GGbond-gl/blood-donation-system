package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.blood.domain.entity.BloodCollection;
import java.util.List;

/**
 * 采血记录 Mapper
 */
public interface BloodCollectionMapper extends BaseMapper<BloodCollection> {

    /**
     * 根据献血者ID查询历史采血记录
     */
    List<BloodCollection> selectByDonorId(Long donorId);
}