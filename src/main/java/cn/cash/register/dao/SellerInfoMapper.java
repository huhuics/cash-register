package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.domain.SellerInfo;

public interface SellerInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SellerInfo record);

    Long insertSelective(SellerInfo record);

    SellerInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SellerInfo record);

    int updateByPrimaryKey(SellerInfo record);

    List<SellerInfo> list(SellerInfoQueryRequest request);

    SellerInfo selectBySellerNo(String sellerNo);
}