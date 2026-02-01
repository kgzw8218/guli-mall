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
import com.zzl.gulimall.product.domain.PmsSkuInfo;
import com.zzl.gulimall.product.service.IPmsSkuInfoService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * sku信息Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/info")
public class PmsSkuInfoController extends BaseController
{
    @Autowired
    private IPmsSkuInfoService pmsSkuInfoService;

    /**
     * 查询sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuInfo pmsSkuInfo)
    {
        startPage();
        List<PmsSkuInfo> list = pmsSkuInfoService.selectPmsSkuInfoList(pmsSkuInfo);
        return getDataTable(list);
    }

    /**
     * 导出sku信息列表
     */
    @PreAuthorize("@ss.hasPermi('product:info:export')")
    @Log(title = "sku信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuInfo pmsSkuInfo)
    {
        List<PmsSkuInfo> list = pmsSkuInfoService.selectPmsSkuInfoList(pmsSkuInfo);
        ExcelUtil<PmsSkuInfo> util = new ExcelUtil<PmsSkuInfo>(PmsSkuInfo.class);
        util.exportExcel(response, list, "sku信息数据");
    }

    /**
     * 获取sku信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:query')")
    @GetMapping(value = "/{skuId}")
    public AjaxResult getInfo(@PathVariable("skuId") Long skuId)
    {
        return success(pmsSkuInfoService.selectPmsSkuInfoBySkuId(skuId));
    }

    /**
     * 新增sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:add')")
    @Log(title = "sku信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuInfo pmsSkuInfo)
    {
        return toAjax(pmsSkuInfoService.insertPmsSkuInfo(pmsSkuInfo));
    }

    /**
     * 修改sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:edit')")
    @Log(title = "sku信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuInfo pmsSkuInfo)
    {
        return toAjax(pmsSkuInfoService.updatePmsSkuInfo(pmsSkuInfo));
    }

    /**
     * 删除sku信息
     */
    @PreAuthorize("@ss.hasPermi('product:info:remove')")
    @Log(title = "sku信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{skuIds}")
    public AjaxResult remove(@PathVariable Long[] skuIds)
    {
        return toAjax(pmsSkuInfoService.deletePmsSkuInfoBySkuIds(skuIds));
    }
}
