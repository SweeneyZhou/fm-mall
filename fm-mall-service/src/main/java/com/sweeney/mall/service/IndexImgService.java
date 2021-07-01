package com.sweeney.mall.service;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.entity.IndexImg;

import java.util.List;

/**
 * @author sweeney
 * @since 2021/07/01 10:44 created.
 */
public interface IndexImgService {
    ResultVO<List<IndexImg>> listIndexImages();
}
