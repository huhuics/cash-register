package cn.cash.register.dao;

import cn.cash.register.dao.domain.SellerInfo;

public interface SellerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerInfo record);

    int insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);
}