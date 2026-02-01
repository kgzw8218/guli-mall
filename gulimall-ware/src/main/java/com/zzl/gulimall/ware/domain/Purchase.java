package com.zzl.gulimall.ware.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.zzl.gulimall.common.annotation.Excel;
import com.zzl.gulimall.common.core.domain.BaseEntity;

/**
 * 采购信息对象 wms_purchase
 * 
 * @author zzl
 * @date 2026-02-01
 */
public class Purchase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 采购单id */
    private Long id;

    /** 采购人id */
    @Excel(name = "采购人id")
    private Long assigneeId;

    /** 采购人名 */
    @Excel(name = "采购人名")
    private String assigneeName;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String phone;

    /** 优先级 */
    @Excel(name = "优先级")
    private Integer priority;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 仓库id */
    @Excel(name = "仓库id")
    private Long wareId;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal amount;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setAssigneeId(Long assigneeId) 
    {
        this.assigneeId = assigneeId;
    }

    public Long getAssigneeId() 
    {
        return assigneeId;
    }

    public void setAssigneeName(String assigneeName) 
    {
        this.assigneeName = assigneeName;
    }

    public String getAssigneeName() 
    {
        return assigneeName;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setPriority(Integer priority) 
    {
        this.priority = priority;
    }

    public Integer getPriority() 
    {
        return priority;
    }

    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    public void setWareId(Long wareId) 
    {
        this.wareId = wareId;
    }

    public Long getWareId() 
    {
        return wareId;
    }

    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("assigneeId", getAssigneeId())
            .append("assigneeName", getAssigneeName())
            .append("phone", getPhone())
            .append("priority", getPriority())
            .append("status", getStatus())
            .append("wareId", getWareId())
            .append("amount", getAmount())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
