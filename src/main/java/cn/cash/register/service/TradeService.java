/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;

/**
 * 交易服务接口
 * <p>提供收款、退货、反收款、订单查询等交易类接口</p>
 * @author HuHui
 * @version $Id: TradeService.java, v 0.1 2018年4月21日 上午9:37:09 HuHui Exp $
 */
public interface TradeService {

    /**
     * 收银
     * TODO
     */
    boolean checkout();

    /**
     * 销售单据分页查询
     */
    PageInfo<TradeDetail> queryTradeDetailList(TradeDetailQueryRequest request);

    /**
     * 商品销售明细分页查询
     */
    PageInfo<TradeGoodsDetail> queryTradeGoodsDetailList(TradeGoodsDetailQueryRequest request);

}
