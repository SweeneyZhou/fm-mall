package com.sweeney.mall.service.impl;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.dao.CategoryMapper;
import com.sweeney.mall.entity.Category;
import com.sweeney.mall.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sweeney
 * @since 2021/07/01 20:54 created.
 */
@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Override
    public ResultVO<List<Category>> listCategory() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("categoryLevel", 1);
        List<Category> categories = categoryMapper.selectByExample(example);
        for (Category category : categories) {
            category.setCategories(getChildrenList(category.getCategoryId()));
        }
        return ResultVO.ok("success", categories);
    }

    //TODO 递归是否安全?
    private List<Category> getChildrenList(Integer categoryId) {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("parentId", categoryId);
        List<Category> categories = categoryMapper.selectByExample(example);
        for (Category category : categories) {
            category.setCategories(getChildrenList(category.getCategoryId()));
        }
        return categories;
    }
}
