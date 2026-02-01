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
import com.zzl.gulimall.product.domain.PmsProductAttrValue;
import com.zzl.gulimall.product.service.IPmsProductAttrValueService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * spu属性值Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/value")
public class PmsProductAttrValueController extends BaseController
{
    @Autowired
    private IPmsProductAttrValueService pmsProductAttrValueService;

    /**
     * 查询spu属性值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsProductAttrValue pmsProductAttrValue)
    {
        startPage();
        List<PmsProductAttrValue> list = pmsProductAttrValueService.selectPmsProductAttrValueList(pmsProductAttrValue);
        return getDataTable(list);
    }

    /**
     * 导出spu属性值列表
     */
    @PreAuthorize("@ss.hasPermi('product:value:export')")
    @Log(title = "spu属性值", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsProductAttrValue pmsProductAttrValue)
    {
        List<PmsProductAttrValue> list = pmsProductAttrValueService.selectPmsProductAttrValueList(pmsProductAttrValue);
        ExcelUtil<PmsProductAttrValue> util = new ExcelUtil<PmsProductAttrValue>(PmsProductAttrValue.class);
        util.exportExcel(response, list, "spu属性值数据");
    }

    /**
     * 获取spu属性值详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:value:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsProductAttrValueService.selectPmsProductAttrValueById(id));
    }

    /**
     * 新增spu属性值
     */
    @PreAuthorize("@ss.hasPermi('product:value:add')")
    @Log(title = "spu属性值", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsProductAttrValue pmsProductAttrValue)
    {
        return toAjax(pmsProductAttrValueService.insertPmsProductAttrValue(pmsProductAttrValue));
    }

    /**
     * 修改spu属性值
     */
    @PreAuthorize("@ss.hasPermi('product:value:edit')")
    @Log(title = "spu属性值", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsProductAttrValue pmsProductAttrValue)
    {
        return toAjax(pmsProductAttrValueService.updatePmsProductAttrValue(pmsProductAttrValue));
    }

    /**
     * 删除spu属性值
     */
    @PreAuthorize("@ss.hasPermi('product:value:remove')")
    @Log(title = "spu属性值", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsProductAttrValueService.deletePmsProductAttrValueByIds(ids));
    }
}
