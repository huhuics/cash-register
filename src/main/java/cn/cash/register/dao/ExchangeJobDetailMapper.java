package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.dao.domain.ExchangeJobDetail;

public interface ExchangeJobDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExchangeJobDetail record);

    Long insertSelective(ExchangeJobDetail record);

    ExchangeJobDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ExchangeJobDetail record);

    int updateByPrimaryKey(ExchangeJobDetail record);

    ExchangeJobDetail selectUnfinished(String sellerNo);

    List<ExchangeJobDetail> list(ExchangeJobQueryRequest request);
}