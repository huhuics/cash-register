package cn.cash.register.dao;

import cn.cash.register.dao.domain.SellerPermissionInfo;

public interface SellerPermissionInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerPermissionInfo record);

    int insertSelective(SellerPermissionInfo record);

    SellerPermissionInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerPermissionInfo record);

    int updateByPrimaryKey(SellerPermissionInfo record);
}