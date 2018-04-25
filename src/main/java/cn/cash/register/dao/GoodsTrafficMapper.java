package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.GoodsTrafficQueryRequest;
import cn.cash.register.dao.domain.GoodsTraffic;

public interface GoodsTrafficMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsTraffic record);

    Long insertSelective(GoodsTraffic record);

    GoodsTraffic selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsTraffic record);

    int updateByPrimaryKey(GoodsTraffic record);

    List<GoodsTraffic> list(GoodsTrafficQueryRequest request);
}