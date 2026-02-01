package com.zzl.gulimall.coupon.controller;

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
import com.zzl.gulimall.coupon.domain.SeckillSkuRelation;
import com.zzl.gulimall.coupon.service.ISeckillSkuRelationService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 秒杀活动商品关联Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/coupon/relation")
public class SeckillSkuRelationController extends BaseController
{
    @Autowired
    private ISeckillSkuRelationService seckillSkuRelationService;

    /**
     * 查询秒杀活动商品关联列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeckillSkuRelation seckillSkuRelation)
    {
        startPage();
        List<SeckillSkuRelation> list = seckillSkuRelationService.selectSeckillSkuRelationList(seckillSkuRelation);
        return getDataTable(list);
    }

    /**
     * 导出秒杀活动商品关联列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:export')")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillSkuRelation seckillSkuRelation)
    {
        List<SeckillSkuRelation> list = seckillSkuRelationService.selectSeckillSkuRelationList(seckillSkuRelation);
        ExcelUtil<SeckillSkuRelation> util = new ExcelUtil<SeckillSkuRelation>(SeckillSkuRelation.class);
        util.exportExcel(response, list, "秒杀活动商品关联数据");
    }

    /**
     * 获取秒杀活动商品关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(seckillSkuRelationService.selectSeckillSkuRelationById(id));
    }

    /**
     * 新增秒杀活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:add')")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return toAjax(seckillSkuRelationService.insertSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 修改秒杀活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:edit')")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillSkuRelation seckillSkuRelation)
    {
        return toAjax(seckillSkuRelationService.updateSeckillSkuRelation(seckillSkuRelation));
    }

    /**
     * 删除秒杀活动商品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:remove')")
    @Log(title = "秒杀活动商品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillSkuRelationService.deleteSeckillSkuRelationByIds(ids));
    }
}
