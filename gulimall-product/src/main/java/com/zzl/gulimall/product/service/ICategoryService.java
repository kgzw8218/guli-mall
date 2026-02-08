package com.zzl.gulimall.product.service;

import java.util.List;
import com.zzl.gulimall.product.domain.Category;
import org.springframework.stereotype.Service;

/**
 * 商品三级分类Service接口
 * 
 * @author zzl
 * @date 2026-02-01
 */
public interface ICategoryService 
{
    /**
     * 以树状查询商品信息
     * @return
     */
    List<Category> listWithTree();
}
