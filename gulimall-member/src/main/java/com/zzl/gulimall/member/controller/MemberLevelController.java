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
import com.zzl.gulimall.member.domain.MemberLevel;
import com.zzl.gulimall.member.service.IMemberLevelService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 会员等级Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/member/level")
public class MemberLevelController extends BaseController
{
    @Autowired
    private IMemberLevelService memberLevelService;

    /**
     * 查询会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('member:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(MemberLevel memberLevel)
    {
        startPage();
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        return getDataTable(list);
    }

    /**
     * 导出会员等级列表
     */
    @PreAuthorize("@ss.hasPermi('member:level:export')")
    @Log(title = "会员等级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MemberLevel memberLevel)
    {
        List<MemberLevel> list = memberLevelService.selectMemberLevelList(memberLevel);
        ExcelUtil<MemberLevel> util = new ExcelUtil<MemberLevel>(MemberLevel.class);
        util.exportExcel(response, list, "会员等级数据");
    }

    /**
     * 获取会员等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('member:level:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(memberLevelService.selectMemberLevelById(id));
    }

    /**
     * 新增会员等级
     */
    @PreAuthorize("@ss.hasPermi('member:level:add')")
    @Log(title = "会员等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MemberLevel memberLevel)
    {
        return toAjax(memberLevelService.insertMemberLevel(memberLevel));
    }

    /**
     * 修改会员等级
     */
    @PreAuthorize("@ss.hasPermi('member:level:edit')")
    @Log(title = "会员等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MemberLevel memberLevel)
    {
        return toAjax(memberLevelService.updateMemberLevel(memberLevel));
    }

    /**
     * 删除会员等级
     */
    @PreAuthorize("@ss.hasPermi('member:level:remove')")
    @Log(title = "会员等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(memberLevelService.deleteMemberLevelByIds(ids));
    }
}
