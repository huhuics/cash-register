package cn.cash.register.dao;

import cn.cash.register.dao.domain.ShopperInfo;

public interface ShopperInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ShopperInfo record);

    int insertSelective(ShopperInfo record);

    ShopperInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ShopperInfo record);

    int updateByPrimaryKey(ShopperInfo record);
}