/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.dao.domain.GoodsImage;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.enums.UpdateFieldEnum;

/**
 * 商品信息服务接口
 * @author HuHui
 * @version $Id: GoodsInfoService.java, v 0.1 2018年4月19日 上午11:42:29 HuHui Exp $
 */
public interface GoodsInfoService {

    /**
     * 增加商品
     * 返回主键id
     */
    Long add(GoodsInfo goodsInfo);

    /**
     * 根据id删除商品
     */
    void delete(List<Long> ids);

    /**
     * 修改商品
     */
    int update(GoodsInfo goodsInfo);

    /**
     * 修改商品库存
     * @param id     商品id
     * @param count  变动数量,可为正/负
     */
    int updateStock(Long id, int count);

    /**
     * 根据id查询商品
     */
    GoodsInfo queryById(Long id);

    /**
     * 翻页查询
     */
    PageInfo<GoodsInfo> queryList(GoodsInfoQueryRequest request);

    /**
     * 修改商品图片
     * 商品图片的增、删、改都可以调用此方法,删除的时候图片数组传null
     * @param goodsInfoId  商品id
     * @param goodsImage   图片二进制数据
     */
    void updateGoodsImage(Long goodsInfoId, byte[] goodsImage);

    /**
     * 查询商品图片
     * @param goodsImageId  商品图片id
     */
    GoodsImage queryGoodsImage(Long goodsImageId);

    /**
     * 批量操作
     */
    void batchUpdate(List<Long> goodsIds, Object newValue, UpdateFieldEnum filedEnum);

}
