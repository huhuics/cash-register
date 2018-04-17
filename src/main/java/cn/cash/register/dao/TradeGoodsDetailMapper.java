package cn.cash.register.dao;

import cn.cash.register.dao.domain.TradeGoodsDetail;

public interface TradeGoodsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeGoodsDetail record);

    int insertSelective(TradeGoodsDetail record);

    TradeGoodsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeGoodsDetail record);

    int updateByPrimaryKey(TradeGoodsDetail record);
}