package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsColor;

public interface GoodsColorMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsColor record);

    Long insertSelective(GoodsColor record);

    GoodsColor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsColor record);

    int updateByPrimaryKey(GoodsColor record);

    List<GoodsColor> selectAll();
}