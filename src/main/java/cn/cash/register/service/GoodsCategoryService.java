/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.alibaba.fastjson.JSONArray;

import cn.cash.register.dao.domain.GoodsCategory;

/**
 * 商品种类服务接口
 * @author HuHui
 * @version $Id: GoodsCategoryService.java, v 0.1 2018年4月17日 下午5:10:52 HuHui Exp $
 */
public interface GoodsCategoryService {

    /**
     * 增加商品种类
     * <p>传入的GoodsCategory参数没有ID字段,增加成功后返回记录的<strong>主键<strong></p>
     * @param category 商品种类对象
     */
    long add(GoodsCategory category);

    /**
     * 删除商品种类
     * <p>该删除方法会将该结点及其子孙结点都删除</p>
     * @param categoryId
     */
    void delete(Long categoryId);

    /**
     * 修改商品种类
     * @return
     */
    int update(GoodsCategory category);

    /**
     * 获取商品种类树
     * @param parentCategoryId 当查询整棵树时填0,即所有首层节点的父id
     * @return json数组
     */
    JSONArray getTree(Long parentCategoryId);
    
    /**
     * 获取商品种类列表
     * @param parentCategoryId 当查询整个列表时填0,即所有首层节点的父id
     * @return json数组
     */
    JSONArray getList(Long parentCategoryId);

}
