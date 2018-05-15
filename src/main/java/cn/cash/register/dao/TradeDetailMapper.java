package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.ExchangeJobTradeDetailRequest;
import cn.cash.register.common.request.MemberPayChanelQueryRequest;
import cn.cash.register.common.request.SalesAmountQueryRequest;
import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.dao.domain.SalesAmountChart;
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

    List<TradeDetail> queryByTradeTime(SalesBasicFactsQueryRequest request);

    List<TradeDetail> queryPaychanel(MemberPayChanelQueryRequest request);

    List<SalesAmountChart> querySalesAmountByHour(SalesAmountQueryRequest request);

    List<SalesAmountChart> querySalesAmountByDay(SalesAmountQueryRequest request);

    List<SalesAmountChart> querySalesAmountByMonty(SalesAmountQueryRequest request);
}