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
import com.zzl.gulimall.member.domain.MemberStatisticsInfo;
import com.zzl.gulimall.member.service.IMemberStatisticsInfoService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 会员统计信息Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/member/info")
public class MemberStatisticsInfoController extends BaseController
{
    @Autowired
    private IMemberStatisticsInfoService memberStatisticsInfoService;

    /**
     * 查询会员统计信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberStatisticsInfo memberStatisticsInfo)
    {
        startPage();
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.selectMemberStatisticsInfoList(memberStatisticsInfo);
        return getDataTable(list);
    }

    /**
     * 导出会员统计信息列表
     */
    @PreAuthorize("@ss.hasPermi('member:info:export')")
    @Log(title = "会员统计信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberStatisticsInfo memberStatisticsInfo)
    {
        List<MemberStatisticsInfo> list = memberStatisticsInfoService.selectMemberStatisticsInfoList(memberStatisticsInfo);
        ExcelUtil<MemberStatisticsInfo> util = new ExcelUtil<MemberStatisticsInfo>(MemberStatisticsInfo.class);
        util.exportExcel(response, list, "会员统计信息数据");
    }

    /**
     * 获取会员统计信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:info:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(memberStatisticsInfoService.selectMemberStatisticsInfoById(id));
    }

    /**
     * 新增会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('member:info:add')")
    @Log(title = "会员统计信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return toAjax(memberStatisticsInfoService.insertMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 修改会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('member:info:edit')")
    @Log(title = "会员统计信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberStatisticsInfo memberStatisticsInfo)
    {
        return toAjax(memberStatisticsInfoService.updateMemberStatisticsInfo(memberStatisticsInfo));
    }

    /**
     * 删除会员统计信息
     */
    @PreAuthorize("@ss.hasPermi('member:info:remove')")
    @Log(title = "会员统计信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberStatisticsInfoService.deleteMemberStatisticsInfoByIds(ids));
    }
}
