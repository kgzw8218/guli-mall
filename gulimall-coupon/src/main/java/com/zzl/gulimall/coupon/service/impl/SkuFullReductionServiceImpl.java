package com.zzl.gulimall.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzl.gulimall.coupon.mapper.SkuFullReductionMapper;
import com.zzl.gulimall.coupon.domain.SkuFullReduction;
import com.zzl.gulimall.coupon.service.ISkuFullReductionService;

/**
 * 商品满减信息Service业务层处理
 * 
 * @author zzl
 * @date 2026-02-01
 */
@Service
public class SkuFullReductionServiceImpl implements ISkuFullReductionService 
{
    @Autowired
    private SkuFullReductionMapper skuFullReductionMapper;

    /**
     * 查询商品满减信息
     * 
     * @param id 商品满减信息主键
     * @return 商品满减信息
     */
    @Override
    public SkuFullReduction selectSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.selectSkuFullReductionById(id);
    }

    /**
     * 查询商品满减信息列表
     * 
     * @param skuFullReduction 商品满减信息
     * @return 商品满减信息
     */
    @Override
    public List<SkuFullReduction> selectSkuFullReductionList(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.selectSkuFullReductionList(skuFullReduction);
    }

    /**
     * 新增商品满减信息
     * 
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int insertSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.insertSkuFullReduction(skuFullReduction);
    }

    /**
     * 修改商品满减信息
     * 
     * @param skuFullReduction 商品满减信息
     * @return 结果
     */
    @Override
    public int updateSkuFullReduction(SkuFullReduction skuFullReduction)
    {
        return skuFullReductionMapper.updateSkuFullReduction(skuFullReduction);
    }

    /**
     * 批量删除商品满减信息
     * 
     * @param ids 需要删除的商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionByIds(Long[] ids)
    {
        return skuFullReductionMapper.deleteSkuFullReductionByIds(ids);
    }

    /**
     * 删除商品满减信息信息
     * 
     * @param id 商品满减信息主键
     * @return 结果
     */
    @Override
    public int deleteSkuFullReductionById(Long id)
    {
        return skuFullReductionMapper.deleteSkuFullReductionById(id);
    }
}
