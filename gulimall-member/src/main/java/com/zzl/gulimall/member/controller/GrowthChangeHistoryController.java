package com.zzl.gulimall.member.controller;

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
import com.zzl.gulimall.member.domain.GrowthChangeHistory;
import com.zzl.gulimall.member.service.IGrowthChangeHistoryService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 成长值变化历史记录Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/member/history")
public class GrowthChangeHistoryController extends BaseController
{
    @Autowired
    private IGrowthChangeHistoryService growthChangeHistoryService;

    /**
     * 查询成长值变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('member:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(GrowthChangeHistory growthChangeHistory)
    {
        startPage();
        List<GrowthChangeHistory> list = growthChangeHistoryService.selectGrowthChangeHistoryList(growthChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出成长值变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('member:history:export')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GrowthChangeHistory growthChangeHistory)
    {
        List<GrowthChangeHistory> list = growthChangeHistoryService.selectGrowthChangeHistoryList(growthChangeHistory);
        ExcelUtil<GrowthChangeHistory> util = new ExcelUtil<GrowthChangeHistory>(GrowthChangeHistory.class);
        util.exportExcel(response, list, "成长值变化历史记录数据");
    }

    /**
     * 获取成长值变化历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(growthChangeHistoryService.selectGrowthChangeHistoryById(id));
    }

    /**
     * 新增成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:add')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return toAjax(growthChangeHistoryService.insertGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 修改成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:edit')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GrowthChangeHistory growthChangeHistory)
    {
        return toAjax(growthChangeHistoryService.updateGrowthChangeHistory(growthChangeHistory));
    }

    /**
     * 删除成长值变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:remove')")
    @Log(title = "成长值变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(growthChangeHistoryService.deleteGrowthChangeHistoryByIds(ids));
    }
}
