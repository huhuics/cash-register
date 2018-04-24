package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.common.request.SupplierQueryRequest;
import cn.cash.register.dao.domain.SupplierInfo;

public interface SupplierInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupplierInfo record);

    Long insertSelective(SupplierInfo record);

    SupplierInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupplierInfo record);

    int updateByPrimaryKey(SupplierInfo record);

    List<SupplierInfo> list(SupplierQueryRequest request);

    List<String> selectAllNames();
}