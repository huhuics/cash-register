package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsBrand;

public interface GoodsBrandMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsBrand record);

    Long insertSelective(GoodsBrand record);

    GoodsBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsBrand record);

    int updateByPrimaryKey(GoodsBrand record);

    List<GoodsBrand> selectAll();
}