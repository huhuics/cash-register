/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.common.request.GoodsStockCheckQueryRequest;
import cn.cash.register.dao.domain.GoodsStockCheck;
import cn.cash.register.dao.domain.GoodsStockCheckDetail;

/**
 * 盘点服务接口
 * @author HuHui
 * @version $Id: GoodsStockCheckServie.java, v 0.1 2018年5月9日 下午8:51:02 HuHui Exp $
 */
public interface GoodsStockCheckServie {

    /**
     * 增加盘点记录
     */
    Long addCheck(String sellerNo, String remark, String detailsStr);

    /**
     * 盘点记录查询
     */
    List<GoodsStockCheck> queryCheck(GoodsStockCheckQueryRequest request);

    /**
     * 根据盘点记录查询盘点商品明细
     * @param stockCheckId 盘点记录id
     */
    List<GoodsStockCheckDetail> queryCheckDetail(Long stockCheckId);

}
