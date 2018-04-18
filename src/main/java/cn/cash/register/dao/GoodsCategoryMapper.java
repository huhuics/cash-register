package cn.cash.register.dao;

import java.util.List;

import cn.cash.register.dao.domain.GoodsCategory;

public interface GoodsCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GoodsCategory record);

    int insertSelective(GoodsCategory record);

    GoodsCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GoodsCategory record);

    int updateByPrimaryKey(GoodsCategory record);

    /**
     * 插入记录并返回主键
     */
    Long insertWithKey(GoodsCategory record);

    /**
     * 删除id所代表的节点的子节点
     */
    void deleteChildren(Long parentId);

    /**
     * 查找子节点
     * @param parentId 父节点id
     */
    List<GoodsCategory> selectByParentId(Long parentId);

}