/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.CancelRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.common.request.TradeRequest;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.ResultSet;

/**
 * 交易服务Controller
 * @author HuHui
 * @version $Id: TradeController.java, v 0.1 2018年5月2日 下午3:12:07 HuHui Exp $
 */
@Controller
@RequestMapping("/cashier/trade")
public class TradeController {

    @Resource
    private TradeService tradeService;

    /**
     * 收银
     */
    @ResponseBody
    @RequestMapping(value = "/checkout")
    public ResultSet checkout(TradeRequest request) {
        boolean ret = tradeService.checkout(request);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 退款
     */
    @ResponseBody
    @RequestMapping(value = "/refund")
    public ResultSet refund(TradeRequest request) {
        boolean ret = tradeService.refund(request);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 反结账
     */
    @ResponseBody
    @RequestMapping(value = "/cancel")
    public ResultSet cancel(CancelRequest request) {
        boolean ret = tradeService.cancel(request);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 销售单据分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryTradeDetailList")
    public ResultSet queryTradeDetailList(TradeDetailQueryRequest request) {
        PageInfo<TradeDetail> tradeDetail = tradeService.queryTradeDetailList(request);
        return ResultSet.success().put("tradeDetail", tradeDetail);
    }

    /**
     * 商品销售明细分页查询
     */
    @ResponseBody
    @RequestMapping(value = "/queryTradeGoodsDetailList")
    public ResultSet queryTradeGoodsDetailList(TradeGoodsDetailQueryRequest request) {
        PageInfo<TradeGoodsDetail> tradeGoodsDetail = tradeService.queryTradeGoodsDetailList(request);
        return ResultSet.success().put("tradeGoodsDetail", tradeGoodsDetail);
    }

}
