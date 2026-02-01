package com.zzl.gulimall.coupon.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzl.gulimall.common.annotation.Excel;
import com.zzl.gulimall.common.core.domain.BaseEntity;

/**
 * 优惠券与产品关联对象 sms_coupon_spu_relation
 * 
 * @author zzl
 * @date 2026-02-01
 */
public class CouponSpuRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 优惠券id */
    @Excel(name = "优惠券id")
    private Long couponId;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long spuId;

    /** spu_name */
    @Excel(name = "spu_name")
    private String spuName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setCouponId(Long couponId) 
    {
        this.couponId = couponId;
    }

    public Long getCouponId() 
    {
        return couponId;
    }

    public void setSpuId(Long spuId) 
    {
        this.spuId = spuId;
    }

    public Long getSpuId() 
    {
        return spuId;
    }

    public void setSpuName(String spuName) 
    {
        this.spuName = spuName;
    }

    public String getSpuName() 
    {
        return spuName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("couponId", getCouponId())
            .append("spuId", getSpuId())
            .append("spuName", getSpuName())
            .toString();
    }
}
