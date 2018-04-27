package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.GoodsLoseInfoQueryRequest;
import cn.cash.register.dao.domain.GoodsLoseInfo;

public interface GoodsLoseInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsLoseInfo record);

    Long insertSelective(GoodsLoseInfo record);

    GoodsLoseInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsLoseInfo record);

    int updateByPrimaryKey(GoodsLoseInfo record);

    List<GoodsLoseInfo> query(GoodsLoseInfoQueryRequest request);

}