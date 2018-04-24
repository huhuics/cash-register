package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsQuantityUnit;

public interface GoodsQuantityUnitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsQuantityUnit record);

    Long insertSelective(GoodsQuantityUnit record);

    GoodsQuantityUnit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsQuantityUnit record);

    int updateByPrimaryKey(GoodsQuantityUnit record);

    List<GoodsQuantityUnit> selectAll();
}