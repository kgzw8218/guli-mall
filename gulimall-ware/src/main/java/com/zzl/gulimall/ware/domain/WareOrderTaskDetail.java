package com.zzl.gulimall.ware.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzl.gulimall.common.annotation.Excel;
import com.zzl.gulimall.common.core.domain.BaseEntity;

/**
 * 库存工作单对象 wms_ware_order_task_detail
 * 
 * @author zzl
 * @date 2026-02-01
 */
public class WareOrderTaskDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** sku_id */
    @Excel(name = "sku_id")
    private Long skuId;

    /** sku_name */
    @Excel(name = "sku_name")
    private String skuName;

    /** 购买个数 */
    @Excel(name = "购买个数")
    private Long skuNum;

    /** 工作单id */
    @Excel(name = "工作单id")
    private Long taskId;

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

    public void setSkuName(String skuName) 
    {
        this.skuName = skuName;
    }

    public String getSkuName() 
    {
        return skuName;
    }

    public void setSkuNum(Long skuNum) 
    {
        this.skuNum = skuNum;
    }

    public Long getSkuNum() 
    {
        return skuNum;
    }

    public void setTaskId(Long taskId) 
    {
        this.taskId = taskId;
    }

    public Long getTaskId() 
    {
        return taskId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("skuId", getSkuId())
            .append("skuName", getSkuName())
            .append("skuNum", getSkuNum())
            .append("taskId", getTaskId())
            .toString();
    }
}
