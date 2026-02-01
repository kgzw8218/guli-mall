package com.zzl.gulimall.coupon.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zzl.gulimall.coupon.mapper.MemberPriceMapper;
import com.zzl.gulimall.coupon.domain.MemberPrice;
import com.zzl.gulimall.coupon.service.IMemberPriceService;

/**
 * 商品会员价格Service业务层处理
 * 
 * @author zzl
 * @date 2026-02-01
 */
@Service
public class MemberPriceServiceImpl implements IMemberPriceService 
{
    @Autowired
    private MemberPriceMapper memberPriceMapper;

    /**
     * 查询商品会员价格
     * 
     * @param id 商品会员价格主键
     * @return 商品会员价格
     */
    @Override
    public MemberPrice selectMemberPriceById(Long id)
    {
        return memberPriceMapper.selectMemberPriceById(id);
    }

    /**
     * 查询商品会员价格列表
     * 
     * @param memberPrice 商品会员价格
     * @return 商品会员价格
     */
    @Override
    public List<MemberPrice> selectMemberPriceList(MemberPrice memberPrice)
    {
        return memberPriceMapper.selectMemberPriceList(memberPrice);
    }

    /**
     * 新增商品会员价格
     * 
     * @param memberPrice 商品会员价格
     * @return 结果
     */
    @Override
    public int insertMemberPrice(MemberPrice memberPrice)
    {
        return memberPriceMapper.insertMemberPrice(memberPrice);
    }

    /**
     * 修改商品会员价格
     * 
     * @param memberPrice 商品会员价格
     * @return 结果
     */
    @Override
    public int updateMemberPrice(MemberPrice memberPrice)
    {
        return memberPriceMapper.updateMemberPrice(memberPrice);
    }

    /**
     * 批量删除商品会员价格
     * 
     * @param ids 需要删除的商品会员价格主键
     * @return 结果
     */
    @Override
    public int deleteMemberPriceByIds(Long[] ids)
    {
        return memberPriceMapper.deleteMemberPriceByIds(ids);
    }

    /**
     * 删除商品会员价格信息
     * 
     * @param id 商品会员价格主键
     * @return 结果
     */
    @Override
    public int deleteMemberPriceById(Long id)
    {
        return memberPriceMapper.deleteMemberPriceById(id);
    }
}
