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
import com.zzl.gulimall.coupon.domain.SkuLadder;
import com.zzl.gulimall.coupon.service.ISkuLadderService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 商品阶梯价格Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/coupon/ladder")
public class SkuLadderController extends BaseController
{
    @Autowired
    private ISkuLadderService skuLadderService;

    /**
     * 查询商品阶梯价格列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:list')")
    @GetMapping("/list")
    public TableDataInfo list(SkuLadder skuLadder)
    {
        startPage();
        List<SkuLadder> list = skuLadderService.selectSkuLadderList(skuLadder);
        return getDataTable(list);
    }

    /**
     * 导出商品阶梯价格列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:export')")
    @Log(title = "商品阶梯价格", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SkuLadder skuLadder)
    {
        List<SkuLadder> list = skuLadderService.selectSkuLadderList(skuLadder);
        ExcelUtil<SkuLadder> util = new ExcelUtil<SkuLadder>(SkuLadder.class);
        util.exportExcel(response, list, "商品阶梯价格数据");
    }

    /**
     * 获取商品阶梯价格详细信息
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(skuLadderService.selectSkuLadderById(id));
    }

    /**
     * 新增商品阶梯价格
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:add')")
    @Log(title = "商品阶梯价格", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SkuLadder skuLadder)
    {
        return toAjax(skuLadderService.insertSkuLadder(skuLadder));
    }

    /**
     * 修改商品阶梯价格
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:edit')")
    @Log(title = "商品阶梯价格", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SkuLadder skuLadder)
    {
        return toAjax(skuLadderService.updateSkuLadder(skuLadder));
    }

    /**
     * 删除商品阶梯价格
     */
    @PreAuthorize("@ss.hasPermi('coupon:ladder:remove')")
    @Log(title = "商品阶梯价格", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(skuLadderService.deleteSkuLadderByIds(ids));
    }
}
