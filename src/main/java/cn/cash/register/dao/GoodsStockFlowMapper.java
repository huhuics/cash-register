package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.StockFlowQueryRequest;
import cn.cash.register.dao.domain.GoodsStockFlow;

public interface GoodsStockFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsStockFlow record);

    int insertSelective(GoodsStockFlow record);

    GoodsStockFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsStockFlow record);

    int updateByPrimaryKey(GoodsStockFlow record);

    List<GoodsStockFlow> list(StockFlowQueryRequest request);
}