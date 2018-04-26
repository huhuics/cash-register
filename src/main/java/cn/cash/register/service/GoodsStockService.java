/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service;

import java.util.List;

import cn.cash.register.common.request.StockFlowQueryRequest;
import cn.cash.register.dao.domain.GoodsStockFlow;
import cn.cash.register.enums.StockFlowTypeEnum;

/**
 * 商品库存服务接口
 * @author HuHui
 * @version $Id: GoodsStockFlowService.java, v 0.1 2018年4月26日 上午10:28:06 HuHui Exp $
 */
public interface GoodsStockService {

    /**
     * 记录库存变化
     * @param goodsName  商品名称
     * @param barCode    商品条码
     * @param type       库存变化类型
     * @param flowCount  库存变化数量
     * @param outBizNo   外部流水号
     * @return           1:成功; 0:失败
     */
    int record(String goodsName, String barCode, StockFlowTypeEnum type, int flowCount, String outBizNo);

    /**
     * 查询商品库存变动明细
     */
    List<GoodsStockFlow> query(StockFlowQueryRequest request);

}
