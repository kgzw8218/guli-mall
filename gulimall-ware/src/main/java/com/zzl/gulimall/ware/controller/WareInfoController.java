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
import com.zzl.gulimall.ware.domain.WareInfo;
import com.zzl.gulimall.ware.service.IWareInfoService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 仓库信息Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/ware/info")
public class WareInfoController extends BaseController
{
    @Autowired
    private IWareInfoService wareInfoService;

    /**
     * 查询仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('ware:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(WareInfo wareInfo)
    {
        startPage();
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        return getDataTable(list);
    }

    /**
     * 导出仓库信息列表
     */
    @PreAuthorize("@ss.hasPermi('ware:info:export')")
    @Log(title = "仓库信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WareInfo wareInfo)
    {
        List<WareInfo> list = wareInfoService.selectWareInfoList(wareInfo);
        ExcelUtil<WareInfo> util = new ExcelUtil<WareInfo>(WareInfo.class);
        util.exportExcel(response, list, "仓库信息数据");
    }

    /**
     * 获取仓库信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('ware:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(wareInfoService.selectWareInfoById(id));
    }

    /**
     * 新增仓库信息
     */
    @PreAuthorize("@ss.hasPermi('ware:info:add')")
    @Log(title = "仓库信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.insertWareInfo(wareInfo));
    }

    /**
     * 修改仓库信息
     */
    @PreAuthorize("@ss.hasPermi('ware:info:edit')")
    @Log(title = "仓库信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WareInfo wareInfo)
    {
        return toAjax(wareInfoService.updateWareInfo(wareInfo));
    }

    /**
     * 删除仓库信息
     */
    @PreAuthorize("@ss.hasPermi('ware:info:remove')")
    @Log(title = "仓库信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(wareInfoService.deleteWareInfoByIds(ids));
    }
}
