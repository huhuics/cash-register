package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsStockCheckDetail;

public interface GoodsStockCheckDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsStockCheckDetail record);

    int insertSelective(GoodsStockCheckDetail record);

    GoodsStockCheckDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsStockCheckDetail record);

    int updateByPrimaryKey(GoodsStockCheckDetail record);

    List<GoodsStockCheckDetail> queryByCheckId(Long stockCheckId);
}