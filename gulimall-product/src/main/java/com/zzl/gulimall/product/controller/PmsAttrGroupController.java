package com.zzl.gulimall.product.controller;

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
import com.zzl.gulimall.product.domain.PmsAttrGroup;
import com.zzl.gulimall.product.service.IPmsAttrGroupService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 属性分组Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/group")
public class PmsAttrGroupController extends BaseController
{
    @Autowired
    private IPmsAttrGroupService pmsAttrGroupService;

    /**
     * 查询属性分组列表
     */
    @PreAuthorize("@ss.hasPermi('product:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsAttrGroup pmsAttrGroup)
    {
        startPage();
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        return getDataTable(list);
    }

    /**
     * 导出属性分组列表
     */
    @PreAuthorize("@ss.hasPermi('product:group:export')")
    @Log(title = "属性分组", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsAttrGroup pmsAttrGroup)
    {
        List<PmsAttrGroup> list = pmsAttrGroupService.selectPmsAttrGroupList(pmsAttrGroup);
        ExcelUtil<PmsAttrGroup> util = new ExcelUtil<PmsAttrGroup>(PmsAttrGroup.class);
        util.exportExcel(response, list, "属性分组数据");
    }

    /**
     * 获取属性分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:group:query')")
    @GetMapping(value = "/{attrGroupId}")
    public AjaxResult getInfo(@PathVariable("attrGroupId") Long attrGroupId)
    {
        return success(pmsAttrGroupService.selectPmsAttrGroupByAttrGroupId(attrGroupId));
    }

    /**
     * 新增属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:add')")
    @Log(title = "属性分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.insertPmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 修改属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:edit')")
    @Log(title = "属性分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsAttrGroup pmsAttrGroup)
    {
        return toAjax(pmsAttrGroupService.updatePmsAttrGroup(pmsAttrGroup));
    }

    /**
     * 删除属性分组
     */
    @PreAuthorize("@ss.hasPermi('product:group:remove')")
    @Log(title = "属性分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{attrGroupIds}")
    public AjaxResult remove(@PathVariable Long[] attrGroupIds)
    {
        return toAjax(pmsAttrGroupService.deletePmsAttrGroupByAttrGroupIds(attrGroupIds));
    }
}
