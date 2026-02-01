package com.zzl.gulimall.ware.controller;

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
import com.zzl.gulimall.ware.domain.Purchase;
import com.zzl.gulimall.ware.service.IPurchaseService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 采购信息Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/ware/purchase")
public class PurchaseController extends BaseController
{
    @Autowired
    private IPurchaseService purchaseService;

    /**
     * 查询采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:list')")
    @GetMapping("/list")
    public TableDataInfo list(Purchase purchase)
    {
        startPage();
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        return getDataTable(list);
    }

    /**
     * 导出采购信息列表
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:export')")
    @Log(title = "采购信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Purchase purchase)
    {
        List<Purchase> list = purchaseService.selectPurchaseList(purchase);
        ExcelUtil<Purchase> util = new ExcelUtil<Purchase>(Purchase.class);
        util.exportExcel(response, list, "采购信息数据");
    }

    /**
     * 获取采购信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(purchaseService.selectPurchaseById(id));
    }

    /**
     * 新增采购信息
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:add')")
    @Log(title = "采购信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.insertPurchase(purchase));
    }

    /**
     * 修改采购信息
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:edit')")
    @Log(title = "采购信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Purchase purchase)
    {
        return toAjax(purchaseService.updatePurchase(purchase));
    }

    /**
     * 删除采购信息
     */
    @PreAuthorize("@ss.hasPermi('ware:purchase:remove')")
    @Log(title = "采购信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(purchaseService.deletePurchaseByIds(ids));
    }
}
