package com.sdut.blood.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sdut.blood.domain.entity.Donor;

/**
 * 献血者档案 Mapper
 */
public interface DonorMapper extends BaseMapper<Donor> {

    /**
     * 根据身份证号查询献血者档案
     */
    Donor selectByIdCard(String idCard);

    /**
     * 根据关联用户ID查询献血者档案
     */
    Donor selectByUserId(Long userId);
}