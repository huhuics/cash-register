package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsTag;

public interface GoodsTagMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsTag record);

    Long insertSelective(GoodsTag record);

    GoodsTag selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsTag record);

    int updateByPrimaryKey(GoodsTag record);

    List<GoodsTag> selectAll();
}