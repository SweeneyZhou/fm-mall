package com.sweeney.mall.web;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.entity.Category;
import com.sweeney.mall.entity.IndexImg;
import com.sweeney.mall.service.CategoryService;
import com.sweeney.mall.service.IndexImgService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author sweeney
 * @since 2021/07/01 17:06 created.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/index")
public class IndexController {

    private final IndexImgService indexImgService;
    private final CategoryService categoryService;

    @ApiOperation("查询首页轮播图")
    @GetMapping("/indeximg")
    public ResultVO<List<IndexImg>> listIndexImg() {
        return indexImgService.listIndexImages();
    }

    @ApiOperation("查询所有分类及其所有子分类")
    @GetMapping("/category-list")
    public ResultVO<List<Category>> listCategory() {
        return categoryService.listCategory();
    }
}
