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
import com.zzl.gulimall.member.domain.IntegrationChangeHistory;
import com.zzl.gulimall.member.service.IIntegrationChangeHistoryService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 积分变化历史记录Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/member/history")
public class IntegrationChangeHistoryController extends BaseController
{
    @Autowired
    private IIntegrationChangeHistoryService integrationChangeHistoryService;

    /**
     * 查询积分变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('member:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(IntegrationChangeHistory integrationChangeHistory)
    {
        startPage();
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.selectIntegrationChangeHistoryList(integrationChangeHistory);
        return getDataTable(list);
    }

    /**
     * 导出积分变化历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('member:history:export')")
    @Log(title = "积分变化历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, IntegrationChangeHistory integrationChangeHistory)
    {
        List<IntegrationChangeHistory> list = integrationChangeHistoryService.selectIntegrationChangeHistoryList(integrationChangeHistory);
        ExcelUtil<IntegrationChangeHistory> util = new ExcelUtil<IntegrationChangeHistory>(IntegrationChangeHistory.class);
        util.exportExcel(response, list, "积分变化历史记录数据");
    }

    /**
     * 获取积分变化历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:history:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(integrationChangeHistoryService.selectIntegrationChangeHistoryById(id));
    }

    /**
     * 新增积分变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:add')")
    @Log(title = "积分变化历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return toAjax(integrationChangeHistoryService.insertIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 修改积分变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:edit')")
    @Log(title = "积分变化历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody IntegrationChangeHistory integrationChangeHistory)
    {
        return toAjax(integrationChangeHistoryService.updateIntegrationChangeHistory(integrationChangeHistory));
    }

    /**
     * 删除积分变化历史记录
     */
    @PreAuthorize("@ss.hasPermi('member:history:remove')")
    @Log(title = "积分变化历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(integrationChangeHistoryService.deleteIntegrationChangeHistoryByIds(ids));
    }
}
