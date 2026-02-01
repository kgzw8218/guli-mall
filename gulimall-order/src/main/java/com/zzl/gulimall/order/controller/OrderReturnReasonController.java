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
import com.zzl.gulimall.order.domain.OrderReturnReason;
import com.zzl.gulimall.order.service.IOrderReturnReasonService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 退货原因Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/order/reason")
public class OrderReturnReasonController extends BaseController
{
    @Autowired
    private IOrderReturnReasonService orderReturnReasonService;

    /**
     * 查询退货原因列表
     */
    @PreAuthorize("@ss.hasPermi('order:reason:list')")
    @GetMapping("/list")
    public TableDataInfo list(OrderReturnReason orderReturnReason)
    {
        startPage();
        List<OrderReturnReason> list = orderReturnReasonService.selectOrderReturnReasonList(orderReturnReason);
        return getDataTable(list);
    }

    /**
     * 导出退货原因列表
     */
    @PreAuthorize("@ss.hasPermi('order:reason:export')")
    @Log(title = "退货原因", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, OrderReturnReason orderReturnReason)
    {
        List<OrderReturnReason> list = orderReturnReasonService.selectOrderReturnReasonList(orderReturnReason);
        ExcelUtil<OrderReturnReason> util = new ExcelUtil<OrderReturnReason>(OrderReturnReason.class);
        util.exportExcel(response, list, "退货原因数据");
    }

    /**
     * 获取退货原因详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:reason:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(orderReturnReasonService.selectOrderReturnReasonById(id));
    }

    /**
     * 新增退货原因
     */
    @PreAuthorize("@ss.hasPermi('order:reason:add')")
    @Log(title = "退货原因", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderReturnReason orderReturnReason)
    {
        return toAjax(orderReturnReasonService.insertOrderReturnReason(orderReturnReason));
    }

    /**
     * 修改退货原因
     */
    @PreAuthorize("@ss.hasPermi('order:reason:edit')")
    @Log(title = "退货原因", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderReturnReason orderReturnReason)
    {
        return toAjax(orderReturnReasonService.updateOrderReturnReason(orderReturnReason));
    }

    /**
     * 删除退货原因
     */
    @PreAuthorize("@ss.hasPermi('order:reason:remove')")
    @Log(title = "退货原因", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderReturnReasonService.deleteOrderReturnReasonByIds(ids));
    }
}
