package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.ShopperInfoQueryRequest;
import cn.cash.register.dao.domain.ShopperInfo;

public interface ShopperInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopperInfo record);

    Long insertSelective(ShopperInfo record);

    ShopperInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopperInfo record);

    int updateByPrimaryKey(ShopperInfo record);

    List<ShopperInfo> list(ShopperInfoQueryRequest request);
}