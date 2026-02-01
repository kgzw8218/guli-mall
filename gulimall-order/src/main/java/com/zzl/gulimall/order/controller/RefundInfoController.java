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
import com.zzl.gulimall.order.domain.RefundInfo;
import com.zzl.gulimall.order.service.IRefundInfoService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 退款信息Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/order/info")
public class RefundInfoController extends BaseController
{
    @Autowired
    private IRefundInfoService refundInfoService;

    /**
     * 查询退款信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(RefundInfo refundInfo)
    {
        startPage();
        List<RefundInfo> list = refundInfoService.selectRefundInfoList(refundInfo);
        return getDataTable(list);
    }

    /**
     * 导出退款信息列表
     */
    @PreAuthorize("@ss.hasPermi('order:info:export')")
    @Log(title = "退款信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RefundInfo refundInfo)
    {
        List<RefundInfo> list = refundInfoService.selectRefundInfoList(refundInfo);
        ExcelUtil<RefundInfo> util = new ExcelUtil<RefundInfo>(RefundInfo.class);
        util.exportExcel(response, list, "退款信息数据");
    }

    /**
     * 获取退款信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(refundInfoService.selectRefundInfoById(id));
    }

    /**
     * 新增退款信息
     */
    @PreAuthorize("@ss.hasPermi('order:info:add')")
    @Log(title = "退款信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RefundInfo refundInfo)
    {
        return toAjax(refundInfoService.insertRefundInfo(refundInfo));
    }

    /**
     * 修改退款信息
     */
    @PreAuthorize("@ss.hasPermi('order:info:edit')")
    @Log(title = "退款信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RefundInfo refundInfo)
    {
        return toAjax(refundInfoService.updateRefundInfo(refundInfo));
    }

    /**
     * 删除退款信息
     */
    @PreAuthorize("@ss.hasPermi('order:info:remove')")
    @Log(title = "退款信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(refundInfoService.deleteRefundInfoByIds(ids));
    }
}
