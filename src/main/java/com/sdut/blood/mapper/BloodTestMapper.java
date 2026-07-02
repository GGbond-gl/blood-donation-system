package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.blood.domain.entity.BloodTest;
import java.util.List;

/**
 * 血液检验 Mapper
 */
public interface BloodTestMapper extends BaseMapper<BloodTest> {

    /**
     * 根据采血记录ID查询对应检验记录
     */
    BloodTest selectByCollectionId(Long collectionId);

    /**
     * 查询所有待判定的检验记录
     */
    List<BloodTest> selectPendingJudgeList();
}