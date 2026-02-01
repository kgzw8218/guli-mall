package com.zzl.gulimall.ware.controller;

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
import com.zzl.gulimall.ware.domain.WareSku;
import com.zzl.gulimall.ware.service.IWareSkuService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 商品库存Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/ware/sku")
public class WareSkuController extends BaseController
{
    @Autowired
    private IWareSkuService wareSkuService;

    /**
     * 查询商品库存列表
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareSku wareSku)
    {
        startPage();
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        return getDataTable(list);
    }

    /**
     * 导出商品库存列表
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:export')")
    @Log(title = "商品库存", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareSku wareSku)
    {
        List<WareSku> list = wareSkuService.selectWareSkuList(wareSku);
        ExcelUtil<WareSku> util = new ExcelUtil<WareSku>(WareSku.class);
        util.exportExcel(response, list, "商品库存数据");
    }

    /**
     * 获取商品库存详细信息
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wareSkuService.selectWareSkuById(id));
    }

    /**
     * 新增商品库存
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:add')")
    @Log(title = "商品库存", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.insertWareSku(wareSku));
    }

    /**
     * 修改商品库存
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:edit')")
    @Log(title = "商品库存", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareSku wareSku)
    {
        return toAjax(wareSkuService.updateWareSku(wareSku));
    }

    /**
     * 删除商品库存
     */
    @PreAuthorize("@ss.hasPermi('ware:sku:remove')")
    @Log(title = "商品库存", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareSkuService.deleteWareSkuByIds(ids));
    }
}
