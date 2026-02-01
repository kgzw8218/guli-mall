package com.zzl.gulimall.coupon.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzl.gulimall.common.annotation.Excel;
import com.zzl.gulimall.common.core.domain.BaseEntity;

/**
 * 商品满减信息对象 sms_sku_full_reduction
 * 
 * @author zzl
 * @date 2026-02-01
 */
public class SkuFullReduction extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** spu_id */
    @Excel(name = "spu_id")
    private Long skuId;

    /** 满多少 */
    @Excel(name = "满多少")
    private BigDecimal fullPrice;

    /** 减多少 */
    @Excel(name = "减多少")
    private BigDecimal reducePrice;

    /** 是否参与其他优惠 */
    @Excel(name = "是否参与其他优惠")
    private Integer addOther;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setSkuId(Long skuId) 
    {
        this.skuId = skuId;
    }

    public Long getSkuId() 
    {
        return skuId;
    }

    public void setFullPrice(BigDecimal fullPrice) 
    {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getFullPrice() 
    {
        return fullPrice;
    }

    public void setReducePrice(BigDecimal reducePrice) 
    {
        this.reducePrice = reducePrice;
    }

    public BigDecimal getReducePrice() 
    {
        return reducePrice;
    }

    public void setAddOther(Integer addOther) 
    {
        this.addOther = addOther;
    }

    public Integer getAddOther() 
    {
        return addOther;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skuId", getSkuId())
            .append("fullPrice", getFullPrice())
            .append("reducePrice", getReducePrice())
            .append("addOther", getAddOther())
            .toString();
    }
}
