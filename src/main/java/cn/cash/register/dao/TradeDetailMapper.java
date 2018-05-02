package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.ExchangeJobTradeDetailRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.dao.domain.TradeDetail;

public interface TradeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeDetail record);

    int insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeDetail record);

    int updateByPrimaryKey(TradeDetail record);

    List<TradeDetail> list(TradeDetailQueryRequest request);

    void deleteByTradeNo(String tradeNo);

    List<TradeDetail> selectExchangeJobTradeDetails(ExchangeJobTradeDetailRequest request);
}