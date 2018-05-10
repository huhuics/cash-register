/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.ShopperInfoQueryRequest;
import cn.cash.register.dao.domain.ShopperInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;

/**
 * 导购员服务接口
 * @author HuHui
 * @version $Id: ShopperInfoService.java, v 0.1 2018年5月3日 上午11:13:46 HuHui Exp $
 */
public interface ShopperInfoService {

    /**
     * 增加导购员
     */
    Long add(ShopperInfo info);

    /**
     * 根据id删除导购员
     */
    int delete(Long id);

    /**
     * 修改导购员资料
     */
    int update(ShopperInfo info);

    /**
     * 根据id查询导购员资料
     */
    ShopperInfo queryById(Long id);

    /**
     * 根据条件查询所有导购员资料
     */
    List<ShopperInfo> queryAll(ShopperInfoQueryRequest request);

    /**
     * 翻页查询导购员资料
     */
    PageInfo<ShopperInfo> queryPage(ShopperInfoQueryRequest request);

    /**
     * 查询导购员业绩
     */
    PageInfo<TradeGoodsDetail> queryAchievement(AchievementQueryRequest request);

}
