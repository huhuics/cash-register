/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.dao.domain.GoodsColor;

/**
 * 商品颜色服务接口
 * @author HuHui
 * @version $Id: GoodsColorService.java, v 0.1 2018年4月17日 下午4:45:56 HuHui Exp $
 */
public interface GoodsColorService {

    /**
     * 增加商品颜色
     * @param colors 商品颜色集合
     */
    void addColors(List<GoodsColor> colors);

    /**
     * 查询所有商品颜色
     * @return 商品颜色集合
     */
    List<GoodsColor> queryAll();

    /**
     * 修改商品颜色
     * @param color 待修改对象
     */
    int update(GoodsColor color);

    /**
     * 删除商品颜色
     * @param colorId
     */
    int delete(Long colorId);

}
