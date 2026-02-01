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
import com.zzl.gulimall.product.domain.PmsCommentReplay;
import com.zzl.gulimall.product.service.IPmsCommentReplayService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 商品评价回复关系Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/replay")
public class PmsCommentReplayController extends BaseController
{
    @Autowired
    private IPmsCommentReplayService pmsCommentReplayService;

    /**
     * 查询商品评价回复关系列表
     */
    @PreAuthorize("@ss.hasPermi('product:replay:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsCommentReplay pmsCommentReplay)
    {
        startPage();
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        return getDataTable(list);
    }

    /**
     * 导出商品评价回复关系列表
     */
    @PreAuthorize("@ss.hasPermi('product:replay:export')")
    @Log(title = "商品评价回复关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsCommentReplay pmsCommentReplay)
    {
        List<PmsCommentReplay> list = pmsCommentReplayService.selectPmsCommentReplayList(pmsCommentReplay);
        ExcelUtil<PmsCommentReplay> util = new ExcelUtil<PmsCommentReplay>(PmsCommentReplay.class);
        util.exportExcel(response, list, "商品评价回复关系数据");
    }

    /**
     * 获取商品评价回复关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:replay:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsCommentReplayService.selectPmsCommentReplayById(id));
    }

    /**
     * 新增商品评价回复关系
     */
    @PreAuthorize("@ss.hasPermi('product:replay:add')")
    @Log(title = "商品评价回复关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.insertPmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 修改商品评价回复关系
     */
    @PreAuthorize("@ss.hasPermi('product:replay:edit')")
    @Log(title = "商品评价回复关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsCommentReplay pmsCommentReplay)
    {
        return toAjax(pmsCommentReplayService.updatePmsCommentReplay(pmsCommentReplay));
    }

    /**
     * 删除商品评价回复关系
     */
    @PreAuthorize("@ss.hasPermi('product:replay:remove')")
    @Log(title = "商品评价回复关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsCommentReplayService.deletePmsCommentReplayByIds(ids));
    }
}
