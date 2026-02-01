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
import com.zzl.gulimall.product.domain.PmsSkuImages;
import com.zzl.gulimall.product.service.IPmsSkuImagesService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * sku图片Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/images")
public class PmsSkuImagesController extends BaseController
{
    @Autowired
    private IPmsSkuImagesService pmsSkuImagesService;

    /**
     * 查询sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSkuImages pmsSkuImages)
    {
        startPage();
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        return getDataTable(list);
    }

    /**
     * 导出sku图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:export')")
    @Log(title = "sku图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSkuImages pmsSkuImages)
    {
        List<PmsSkuImages> list = pmsSkuImagesService.selectPmsSkuImagesList(pmsSkuImages);
        ExcelUtil<PmsSkuImages> util = new ExcelUtil<PmsSkuImages>(PmsSkuImages.class);
        util.exportExcel(response, list, "sku图片数据");
    }

    /**
     * 获取sku图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSkuImagesService.selectPmsSkuImagesById(id));
    }

    /**
     * 新增sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:add')")
    @Log(title = "sku图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.insertPmsSkuImages(pmsSkuImages));
    }

    /**
     * 修改sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:edit')")
    @Log(title = "sku图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSkuImages pmsSkuImages)
    {
        return toAjax(pmsSkuImagesService.updatePmsSkuImages(pmsSkuImages));
    }

    /**
     * 删除sku图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:remove')")
    @Log(title = "sku图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSkuImagesService.deletePmsSkuImagesByIds(ids));
    }
}
