/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsTrafficQueryRequest;
import cn.cash.register.common.request.InTrafficRequest;
import cn.cash.register.common.request.OutTrafficRequest;
import cn.cash.register.dao.domain.GoodsTraffic;

/**
 * 商品货流服务接口
 * @author HuHui
 * @version $Id: GoodsTrafficService.java, v 0.1 2018年4月25日 上午10:34:14 HuHui Exp $
 */
public interface GoodsTrafficService {

    /**
     * 商品进货
     * @return 主键id
     */
    Long addInTraffic(InTrafficRequest request);

    /**
     * 商品出库
     * @return 主键id
     */
    Long addOutTraffic(OutTrafficRequest request);

    /**
     * 根据货流主键id查询详情
     * @param id
     */
    GoodsTraffic queryById(Long id);

    /**
     * 货流分页查询
     */
    PageInfo<GoodsTraffic> queryList(GoodsTrafficQueryRequest request);

}
