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
import com.zzl.gulimall.coupon.domain.CouponSpuRelation;
import com.zzl.gulimall.coupon.service.ICouponSpuRelationService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 优惠券与产品关联Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/coupon/relation")
public class CouponSpuRelationController extends BaseController
{
    @Autowired
    private ICouponSpuRelationService couponSpuRelationService;

    /**
     * 查询优惠券与产品关联列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CouponSpuRelation couponSpuRelation)
    {
        startPage();
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        return getDataTable(list);
    }

    /**
     * 导出优惠券与产品关联列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:export')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CouponSpuRelation couponSpuRelation)
    {
        List<CouponSpuRelation> list = couponSpuRelationService.selectCouponSpuRelationList(couponSpuRelation);
        ExcelUtil<CouponSpuRelation> util = new ExcelUtil<CouponSpuRelation>(CouponSpuRelation.class);
        util.exportExcel(response, list, "优惠券与产品关联数据");
    }

    /**
     * 获取优惠券与产品关联详细信息
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(couponSpuRelationService.selectCouponSpuRelationById(id));
    }

    /**
     * 新增优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:add')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.insertCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 修改优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:edit')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CouponSpuRelation couponSpuRelation)
    {
        return toAjax(couponSpuRelationService.updateCouponSpuRelation(couponSpuRelation));
    }

    /**
     * 删除优惠券与产品关联
     */
    @PreAuthorize("@ss.hasPermi('coupon:relation:remove')")
    @Log(title = "优惠券与产品关联", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(couponSpuRelationService.deleteCouponSpuRelationByIds(ids));
    }
}
