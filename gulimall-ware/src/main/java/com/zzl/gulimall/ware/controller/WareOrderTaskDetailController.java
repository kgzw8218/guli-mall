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
import com.zzl.gulimall.ware.domain.WareOrderTaskDetail;
import com.zzl.gulimall.ware.service.IWareOrderTaskDetailService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 库存工作单Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/ware/detail")
public class WareOrderTaskDetailController extends BaseController
{
    @Autowired
    private IWareOrderTaskDetailService wareOrderTaskDetailService;

    /**
     * 查询库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareOrderTaskDetail wareOrderTaskDetail)
    {
        startPage();
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.selectWareOrderTaskDetailList(wareOrderTaskDetail);
        return getDataTable(list);
    }

    /**
     * 导出库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:export')")
    @Log(title = "库存工作单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareOrderTaskDetail wareOrderTaskDetail)
    {
        List<WareOrderTaskDetail> list = wareOrderTaskDetailService.selectWareOrderTaskDetailList(wareOrderTaskDetail);
        ExcelUtil<WareOrderTaskDetail> util = new ExcelUtil<WareOrderTaskDetail>(WareOrderTaskDetail.class);
        util.exportExcel(response, list, "库存工作单数据");
    }

    /**
     * 获取库存工作单详细信息
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wareOrderTaskDetailService.selectWareOrderTaskDetailById(id));
    }

    /**
     * 新增库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:add')")
    @Log(title = "库存工作单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return toAjax(wareOrderTaskDetailService.insertWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 修改库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:edit')")
    @Log(title = "库存工作单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareOrderTaskDetail wareOrderTaskDetail)
    {
        return toAjax(wareOrderTaskDetailService.updateWareOrderTaskDetail(wareOrderTaskDetail));
    }

    /**
     * 删除库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:detail:remove')")
    @Log(title = "库存工作单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareOrderTaskDetailService.deleteWareOrderTaskDetailByIds(ids));
    }
}
