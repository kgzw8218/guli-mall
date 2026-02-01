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
import com.zzl.gulimall.product.domain.PmsSpuComment;
import com.zzl.gulimall.product.service.IPmsSpuCommentService;
import com.zzl.gulimall.common.utils.poi.ExcelUtil;
import com.zzl.gulimall.common.core.page.TableDataInfo;

/**
 * 商品评价Controller
 * 
 * @author zzl
 * @date 2026-02-01
 */
@RestController
@RequestMapping("/product/comment")
public class PmsSpuCommentController extends BaseController
{
    @Autowired
    private IPmsSpuCommentService pmsSpuCommentService;

    /**
     * 查询商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('product:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(PmsSpuComment pmsSpuComment)
    {
        startPage();
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        return getDataTable(list);
    }

    /**
     * 导出商品评价列表
     */
    @PreAuthorize("@ss.hasPermi('product:comment:export')")
    @Log(title = "商品评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PmsSpuComment pmsSpuComment)
    {
        List<PmsSpuComment> list = pmsSpuCommentService.selectPmsSpuCommentList(pmsSpuComment);
        ExcelUtil<PmsSpuComment> util = new ExcelUtil<PmsSpuComment>(PmsSpuComment.class);
        util.exportExcel(response, list, "商品评价数据");
    }

    /**
     * 获取商品评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('product:comment:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pmsSpuCommentService.selectPmsSpuCommentById(id));
    }

    /**
     * 新增商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:add')")
    @Log(title = "商品评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.insertPmsSpuComment(pmsSpuComment));
    }

    /**
     * 修改商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:edit')")
    @Log(title = "商品评价", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PmsSpuComment pmsSpuComment)
    {
        return toAjax(pmsSpuCommentService.updatePmsSpuComment(pmsSpuComment));
    }

    /**
     * 删除商品评价
     */
    @PreAuthorize("@ss.hasPermi('product:comment:remove')")
    @Log(title = "商品评价", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pmsSpuCommentService.deletePmsSpuCommentByIds(ids));
    }
}
