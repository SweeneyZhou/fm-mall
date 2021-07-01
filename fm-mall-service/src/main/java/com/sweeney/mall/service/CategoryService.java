package com.sweeney.mall.service;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.entity.Category;

import java.util.List;

/**
 * @author sweeney
 * @since 2021/07/01 17:48 created.
 */
public interface CategoryService {
    ResultVO<List<Category>> listCategory();
}
