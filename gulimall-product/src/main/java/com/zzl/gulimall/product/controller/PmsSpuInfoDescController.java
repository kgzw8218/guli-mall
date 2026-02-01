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
import com.zzl.gulimall.product.domain.PmsSpuInfoDesc;
import com.zzl.gulimall.product.service.IPmsSpuInfoDescService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * spu信息介绍Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/desc")
public class PmsSpuInfoDescController extends BaseController
{
    @Autowired
    private IPmsSpuInfoDescService pmsSpuInfoDescService;

    /**
     * 查询spu信息介绍列表
     */
    @PreAuthorize("@ss.hasPermi('product:desc:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        startPage();
        List<PmsSpuInfoDesc> list = pmsSpuInfoDescService.selectPmsSpuInfoDescList(pmsSpuInfoDesc);
        return getDataTable(list);
    }

    /**
     * 导出spu信息介绍列表
     */
    @PreAuthorize("@ss.hasPermi('product:desc:export')")
    @Log(title = "spu信息介绍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        List<PmsSpuInfoDesc> list = pmsSpuInfoDescService.selectPmsSpuInfoDescList(pmsSpuInfoDesc);
        ExcelUtil<PmsSpuInfoDesc> util = new ExcelUtil<PmsSpuInfoDesc>(PmsSpuInfoDesc.class);
        util.exportExcel(response, list, "spu信息介绍数据");
    }

    /**
     * 获取spu信息介绍详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:desc:query')")
    @GetMapping(value = "/{spuId}")
    public AjaxResult getInfo(@PathVariable("spuId") Long spuId)
    {
        return success(pmsSpuInfoDescService.selectPmsSpuInfoDescBySpuId(spuId));
    }

    /**
     * 新增spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:add')")
    @Log(title = "spu信息介绍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        return toAjax(pmsSpuInfoDescService.insertPmsSpuInfoDesc(pmsSpuInfoDesc));
    }

    /**
     * 修改spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:edit')")
    @Log(title = "spu信息介绍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuInfoDesc pmsSpuInfoDesc)
    {
        return toAjax(pmsSpuInfoDescService.updatePmsSpuInfoDesc(pmsSpuInfoDesc));
    }

    /**
     * 删除spu信息介绍
     */
    @PreAuthorize("@ss.hasPermi('product:desc:remove')")
    @Log(title = "spu信息介绍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{spuIds}")
    public AjaxResult remove(@PathVariable Long[] spuIds)
    {
        return toAjax(pmsSpuInfoDescService.deletePmsSpuInfoDescBySpuIds(spuIds));
    }
}
