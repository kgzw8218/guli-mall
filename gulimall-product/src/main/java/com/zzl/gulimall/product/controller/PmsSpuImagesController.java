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
import com.zzl.gulimall.product.domain.PmsSpuImages;
import com.zzl.gulimall.product.service.IPmsSpuImagesService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * spu图片Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/images")
public class PmsSpuImagesController extends BaseController
{
    @Autowired
    private IPmsSpuImagesService pmsSpuImagesService;

    /**
     * 查询spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuImages pmsSpuImages)
    {
        startPage();
        List<PmsSpuImages> list = pmsSpuImagesService.selectPmsSpuImagesList(pmsSpuImages);
        return getDataTable(list);
    }

    /**
     * 导出spu图片列表
     */
    @PreAuthorize("@ss.hasPermi('product:images:export')")
    @Log(title = "spu图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuImages pmsSpuImages)
    {
        List<PmsSpuImages> list = pmsSpuImagesService.selectPmsSpuImagesList(pmsSpuImages);
        ExcelUtil<PmsSpuImages> util = new ExcelUtil<PmsSpuImages>(PmsSpuImages.class);
        util.exportExcel(response, list, "spu图片数据");
    }

    /**
     * 获取spu图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:images:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSpuImagesService.selectPmsSpuImagesById(id));
    }

    /**
     * 新增spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:add')")
    @Log(title = "spu图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuImages pmsSpuImages)
    {
        return toAjax(pmsSpuImagesService.insertPmsSpuImages(pmsSpuImages));
    }

    /**
     * 修改spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:edit')")
    @Log(title = "spu图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuImages pmsSpuImages)
    {
        return toAjax(pmsSpuImagesService.updatePmsSpuImages(pmsSpuImages));
    }

    /**
     * 删除spu图片
     */
    @PreAuthorize("@ss.hasPermi('product:images:remove')")
    @Log(title = "spu图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuImagesService.deletePmsSpuImagesByIds(ids));
    }
}
