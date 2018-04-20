package cn.cash.register.dao;

import cn.cash.register.dao.domain.GoodsImage;

public interface GoodsImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsImage record);

    int insertSelective(GoodsImage record);

    GoodsImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsImage record);

    int updateByPrimaryKeyWithBLOBs(GoodsImage record);

    int updateByPrimaryKey(GoodsImage record);

    Long insertWithKey(GoodsImage record);
}