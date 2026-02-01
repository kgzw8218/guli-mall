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
import com.zzl.gulimall.ware.domain.WareOrderTask;
import com.zzl.gulimall.ware.service.IWareOrderTaskService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 库存工作单Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/ware/task")
public class WareOrderTaskController extends BaseController
{
    @Autowired
    private IWareOrderTaskService wareOrderTaskService;

    /**
     * 查询库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('ware:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareOrderTask wareOrderTask)
    {
        startPage();
        List<WareOrderTask> list = wareOrderTaskService.selectWareOrderTaskList(wareOrderTask);
        return getDataTable(list);
    }

    /**
     * 导出库存工作单列表
     */
    @PreAuthorize("@ss.hasPermi('ware:task:export')")
    @Log(title = "库存工作单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareOrderTask wareOrderTask)
    {
        List<WareOrderTask> list = wareOrderTaskService.selectWareOrderTaskList(wareOrderTask);
        ExcelUtil<WareOrderTask> util = new ExcelUtil<WareOrderTask>(WareOrderTask.class);
        util.exportExcel(response, list, "库存工作单数据");
    }

    /**
     * 获取库存工作单详细信息
     */
    @PreAuthorize("@ss.hasPermi('ware:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wareOrderTaskService.selectWareOrderTaskById(id));
    }

    /**
     * 新增库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:task:add')")
    @Log(title = "库存工作单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareOrderTask wareOrderTask)
    {
        return toAjax(wareOrderTaskService.insertWareOrderTask(wareOrderTask));
    }

    /**
     * 修改库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:task:edit')")
    @Log(title = "库存工作单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareOrderTask wareOrderTask)
    {
        return toAjax(wareOrderTaskService.updateWareOrderTask(wareOrderTask));
    }

    /**
     * 删除库存工作单
     */
    @PreAuthorize("@ss.hasPermi('ware:task:remove')")
    @Log(title = "库存工作单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareOrderTaskService.deleteWareOrderTaskByIds(ids));
    }
}
