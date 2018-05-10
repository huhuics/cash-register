package cn.cash.register.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.StockWarningQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsInfo record);

    Long insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    List<GoodsInfo> list(GoodsInfoQueryRequest request);

    int updateStock(@Param("id") Long id, @Param("count") int count);

    List<GoodsInfo> search(String keyword);

    List<GoodsInfo> queryByStock(StockWarningQueryRequest request);

    int updateByBarCodeSelective(GoodsInfo info);

    List<GoodsInfo> listAll();
}