/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.GoodsTag;

/**
 * 商品标签服务接口
 * @author HuHui
 * @version $Id: GoodsTagService.java, v 0.1 2018年4月24日 上午9:25:38 HuHui Exp $
 */
public interface GoodsTagService {

    /**
     * 增加商品标签
     */
    Long add(String tagName);

    /**
     * 通过主键删除商品标签
     */
    int delete(Long id);

    /**
     * 修改商品标签
     */
    int update(GoodsTag tag);

    /**
     * 根据主键查询商品标签
     */
    GoodsTag queryById(Long id);

    /**
     * 查询所有商品标签
     */
    List<GoodsTag> queryAll();

}
