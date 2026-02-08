package com.zzl.gulimall.product.mapper;

import java.util.List;
import com.zzl.gulimall.product.domain.Category;

/**
 * 商品三级分类Mapper接口
 * 
 * @author zzl
 * @date 2026-02-01
 */
public interface CategoryMapper
{

    List<Category> list();

    List<Category> selectCategoryList(Category category);
}
