package cn.cash.register.dao;

import org.apache.ibatis.annotations.Param;

import cn.cash.register.dao.domain.PromotionGoodsDetail;

public interface PromotionGoodsDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PromotionGoodsDetail record);

    int insertSelective(PromotionGoodsDetail record);

    PromotionGoodsDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PromotionGoodsDetail record);

    int updateByPrimaryKey(PromotionGoodsDetail record);

    PromotionGoodsDetail queryGoodsPromotionDetail(@Param("goodsId") Long goodsId, @Param("promotionId") Long promotionId);

    void deleteByPromotionId(Long promotionId);
}