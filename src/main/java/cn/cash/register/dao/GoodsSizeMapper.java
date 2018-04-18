package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsSize;

public interface GoodsSizeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsSize record);

    int insertSelective(GoodsSize record);

    GoodsSize selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsSize record);

    int updateByPrimaryKey(GoodsSize record);

    List<GoodsSize> selectAll();
}