/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PageInfoQueryRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;

/**
 * 商品信息服务接口
 * @author HuHui
 * @version $Id: GoodsInfoService.java, v 0.1 2018年4月19日 上午11:42:29 HuHui Exp $
 */
public interface GoodsInfoService {

    /**
     * 增加商品
     */
    int add(GoodsInfo goodsInfo);

    /**
     * 根据id删除商品
     */
    int delete(Long id);

    /**
     * 修改商品
     */
    int update(GoodsInfo goodsInfo);

    /**
     * 根据id查询商品
     */
    GoodsInfo queryById(Long id);

    /**
     * 翻页查询
     */
    PageInfo<GoodsInfo> queryList(PageInfoQueryRequest request);

    /**
     * 修改商品图片
     * 商品图片的增、删、改都可以调用此方法,删除的时候图片数组传null
     * @param goodsInfoId  商品id
     * @param goodsImage   图片二进制数据
     */
    int updateGoodsImage(Long goodsInfoId, byte[] goodsImage);

    /**
     * 查询商品图片
     * @param goodsImageId  商品图片id
     */
    GoodsImage queryGoodsImage(Long goodsImageId);

}
