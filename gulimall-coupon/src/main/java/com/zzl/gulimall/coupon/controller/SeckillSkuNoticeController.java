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
import com.zzl.gulimall.coupon.domain.SeckillSkuNotice;
import com.zzl.gulimall.coupon.service.ISeckillSkuNoticeService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 秒杀商品通知订阅Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/coupon/notice")
public class SeckillSkuNoticeController extends BaseController
{
    @Autowired
    private ISeckillSkuNoticeService seckillSkuNoticeService;

    /**
     * 查询秒杀商品通知订阅列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:list')")
    @GetMapping("/list")
    public TableDataInfo list(SeckillSkuNotice seckillSkuNotice)
    {
        startPage();
        List<SeckillSkuNotice> list = seckillSkuNoticeService.selectSeckillSkuNoticeList(seckillSkuNotice);
        return getDataTable(list);
    }

    /**
     * 导出秒杀商品通知订阅列表
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:export')")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SeckillSkuNotice seckillSkuNotice)
    {
        List<SeckillSkuNotice> list = seckillSkuNoticeService.selectSeckillSkuNoticeList(seckillSkuNotice);
        ExcelUtil<SeckillSkuNotice> util = new ExcelUtil<SeckillSkuNotice>(SeckillSkuNotice.class);
        util.exportExcel(response, list, "秒杀商品通知订阅数据");
    }

    /**
     * 获取秒杀商品通知订阅详细信息
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(seckillSkuNoticeService.selectSeckillSkuNoticeById(id));
    }

    /**
     * 新增秒杀商品通知订阅
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:add')")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SeckillSkuNotice seckillSkuNotice)
    {
        return toAjax(seckillSkuNoticeService.insertSeckillSkuNotice(seckillSkuNotice));
    }

    /**
     * 修改秒杀商品通知订阅
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:edit')")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SeckillSkuNotice seckillSkuNotice)
    {
        return toAjax(seckillSkuNoticeService.updateSeckillSkuNotice(seckillSkuNotice));
    }

    /**
     * 删除秒杀商品通知订阅
     */
    @PreAuthorize("@ss.hasPermi('coupon:notice:remove')")
    @Log(title = "秒杀商品通知订阅", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(seckillSkuNoticeService.deleteSeckillSkuNoticeByIds(ids));
    }
}
