package cn.cash.register.dao;

import cn.cash.register.dao.domain.TradeDetail;

public interface TradeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TradeDetail record);

    int insertSelective(TradeDetail record);

    TradeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TradeDetail record);

    int updateByPrimaryKey(TradeDetail record);
}