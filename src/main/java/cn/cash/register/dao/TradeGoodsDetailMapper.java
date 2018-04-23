package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.TradeGoodsDetail;

public interface TradeGoodsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeGoodsDetail record);

    int insertSelective(TradeGoodsDetail record);

    TradeGoodsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeGoodsDetail record);

    int updateByPrimaryKey(TradeGoodsDetail record);

    List<TradeGoodsDetail> list(TradeGoodsDetailQueryRequest request);

    void deleteByTradeNo(String tradeNo);
}