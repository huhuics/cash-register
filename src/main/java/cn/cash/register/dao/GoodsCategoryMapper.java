package cn.cash.register.dao;

import cn.cash.register.dao.domain.GoodsCategory;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    /**
     * 插入成功后返回该记录自增id
     * @return
     */
    long insertWithKey(GoodsCategory record);

    int deleteChildren(Long parentId);
}