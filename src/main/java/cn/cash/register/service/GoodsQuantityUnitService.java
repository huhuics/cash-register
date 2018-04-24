/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.GoodsQuantityUnit;

/**
 * 商品单位服务接口
 * @author HuHui
 * @version $Id: GoodsQuantityUnitService.java, v 0.1 2018年4月24日 上午9:25:38 HuHui Exp $
 */
public interface GoodsQuantityUnitService {

    /**
     * 增加商品单位
     */
    Long add(String unitName);

    /**
     * 通过主键删除商品单位
     */
    int delete(Long id);

    /**
     * 修改商品单位
     */
    int update(GoodsQuantityUnit unit);

    /**
     * 根据主键查询商品单位
     */
    GoodsQuantityUnit queryById(Long id);

    /**
     * 查询所有商品单位
     */
    List<GoodsQuantityUnit> queryAll();

}
