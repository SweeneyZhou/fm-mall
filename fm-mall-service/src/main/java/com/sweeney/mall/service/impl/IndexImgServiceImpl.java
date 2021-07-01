package com.sweeney.mall.service.impl;

import com.sweeney.mall.common.vo.ResultVO;
import com.sweeney.mall.dao.IndexImgMapper;
import com.sweeney.mall.entity.IndexImg;
import com.sweeney.mall.service.IndexImgService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import java.util.List;

/**
 * @author sweeney
 * @since 2021/07/01 17:13 created.
 */
@AllArgsConstructor
@Service
public class IndexImgServiceImpl implements IndexImgService {
    private final IndexImgMapper indexImgMapper;

    @Override
    public ResultVO<List<IndexImg>> listIndexImages() {
        Condition condition = new Condition(IndexImg.class);
        Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("status", 1);
        condition.orderBy("seq");
        List<IndexImg> images = indexImgMapper.selectByExample(condition);
        return images.size() > 0 ? ResultVO.ok("success", images) : ResultVO.error("failed", null);
    }
}
