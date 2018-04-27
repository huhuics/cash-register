package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsLoseReason;

public interface GoodsLoseReasonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsLoseReason record);

    Long insertSelective(GoodsLoseReason record);

    GoodsLoseReason selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsLoseReason record);

    int updateByPrimaryKey(GoodsLoseReason record);

    List<GoodsLoseReason> queryAll();
}