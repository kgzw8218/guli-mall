package com.zzl.gulimall.product.controller;

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
import com.zzl.gulimall.product.domain.PmsSkuSaleAttrValue;
import com.zzl.gulimall.product.service.IPmsSkuSaleAttrValueService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * sku销售属性&值Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/value")
public class PmsSkuSaleAttrValueController extends BaseController
{
    @Autowired
    private IPmsSkuSaleAttrValueService pmsSkuSaleAttrValueService;

    /**
     * 查询sku销售属性&值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        startPage();
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出sku销售属性&值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:export')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        List<PmsSkuSaleAttrValue> list = pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueList(pmsSkuSaleAttrValue);
        ExcelUtil<PmsSkuSaleAttrValue> util = new ExcelUtil<PmsSkuSaleAttrValue>(PmsSkuSaleAttrValue.class);
        util.exportExcel(response, list, "sku销售属性&值数据");
    }

    /**
     * 获取sku销售属性&值详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:value:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSkuSaleAttrValueService.selectPmsSkuSaleAttrValueById(id));
    }

    /**
     * 新增sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:add')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        return toAjax(pmsSkuSaleAttrValueService.insertPmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 修改sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:edit')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuSaleAttrValue pmsSkuSaleAttrValue)
    {
        return toAjax(pmsSkuSaleAttrValueService.updatePmsSkuSaleAttrValue(pmsSkuSaleAttrValue));
    }

    /**
     * 删除sku销售属性&值
     */
    @PreAuthorize("@ss.hasPermi('product:value:remove')")
    @Log(title = "sku销售属性&值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSkuSaleAttrValueService.deletePmsSkuSaleAttrValueByIds(ids));
    }
}
