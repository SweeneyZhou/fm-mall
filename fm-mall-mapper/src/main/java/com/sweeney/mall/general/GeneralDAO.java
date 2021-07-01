package com.sweeney.mall.general;


import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author sweeney
 * @since 2021/06/29 19:59 created.
 */
public interface GeneralDAO<T> extends Mapper<T>, MySqlMapper<T> {
}
