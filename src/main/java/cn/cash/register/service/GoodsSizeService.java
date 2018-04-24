/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.GoodsSize;

/**
 * 商品尺寸服务接口
 * @author HuHui
 * @version $Id: GoodsSizeService.java, v 0.1 2018年4月17日 下午4:57:15 HuHui Exp $
 */
public interface GoodsSizeService {

    /**
     * 增加商品尺寸
     * @param sizes 商品尺寸
     * 返回主键
     */
    Long addSize(String sizeName);

    /**
     * 查询所有商品尺寸
     * @return 商品尺寸集合
     */
    List<GoodsSize> queryAll();

    /**
     * 修改商品尺寸
     * @param size 待修改对象
     */
    int update(GoodsSize size);

    /**
     * 删除商品尺寸
     * @param sizeId
     */
    int delete(Long sizeId);

}
