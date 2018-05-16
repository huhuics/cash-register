package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.domain.PromotionDetail;

public interface PromotionDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PromotionDetail record);

    Long insertSelective(PromotionDetail record);

    PromotionDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotionDetail record);

    int updateByPrimaryKey(PromotionDetail record);

    List<PromotionDetail> list(PromotionQueryRequest request);
}