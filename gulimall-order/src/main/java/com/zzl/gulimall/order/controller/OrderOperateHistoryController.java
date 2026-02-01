package com.zzl.gulimall.order.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.zzl.gulimall.common.annotation.Log;
import com.zzl.gulimall.common.core.controller.BaseController;
import com.zzl.gulimall.common.core.domain.AjaxResult;
import com.zzl.gulimall.common.enums.BusinessType;
import com.zzl.gulimall.order.domain.OrderOperateHistory;
import com.zzl.gulimall.order.service.IOrderOperateHistoryService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 订单操作历史记录Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/order/history")
public class OrderOperateHistoryController extends BaseController
{
    @Autowired
    private IOrderOperateHistoryService orderOperateHistoryService;

    /**
     * 查询订单操作历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('order:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderOperateHistory orderOperateHistory)
    {
        startPage();
        List<OrderOperateHistory> list = orderOperateHistoryService.selectOrderOperateHistoryList(orderOperateHistory);
        return getDataTable(list);
    }

    /**
     * 导出订单操作历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('order:history:export')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderOperateHistory orderOperateHistory)
    {
        List<OrderOperateHistory> list = orderOperateHistoryService.selectOrderOperateHistoryList(orderOperateHistory);
        ExcelUtil<OrderOperateHistory> util = new ExcelUtil<OrderOperateHistory>(OrderOperateHistory.class);
        util.exportExcel(response, list, "订单操作历史记录数据");
    }

    /**
     * 获取订单操作历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderOperateHistoryService.selectOrderOperateHistoryById(id));
    }

    /**
     * 新增订单操作历史记录
     */
    @PreAuthorize("@ss.hasPermi('order:history:add')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return toAjax(orderOperateHistoryService.insertOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 修改订单操作历史记录
     */
    @PreAuthorize("@ss.hasPermi('order:history:edit')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderOperateHistory orderOperateHistory)
    {
        return toAjax(orderOperateHistoryService.updateOrderOperateHistory(orderOperateHistory));
    }

    /**
     * 删除订单操作历史记录
     */
    @PreAuthorize("@ss.hasPermi('order:history:remove')")
    @Log(title = "订单操作历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderOperateHistoryService.deleteOrderOperateHistoryByIds(ids));
    }
}
