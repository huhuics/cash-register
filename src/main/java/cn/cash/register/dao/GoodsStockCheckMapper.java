package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.GoodsStockCheckQueryRequest;
import cn.cash.register.dao.domain.GoodsStockCheck;

public interface GoodsStockCheckMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsStockCheck record);

    Long insertSelective(GoodsStockCheck record);

    GoodsStockCheck selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsStockCheck record);

    int updateByPrimaryKey(GoodsStockCheck record);

    List<GoodsStockCheck> query(GoodsStockCheckQueryRequest request);
}