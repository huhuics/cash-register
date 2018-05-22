/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.MemberService;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.ResultSet;

/**
 * 交易服务Controller
 * @author HuHui
 * @version $Id: TradeController.java, v 0.1 2018年5月2日 下午3:12:07 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/trade")
public class TradeController {

    private static final Logger logger = LoggerFactory.getLogger(TradeController.class);

    @Resource
    private TradeService        tradeService;

    @Resource
    private GoodsInfoService    goodsInfoService;

    @Resource
    private MemberService       memberService;

    /**
     * 销售单据分页查询页面
     */
    @RequestMapping(value = "/queryTradeDetailListPage")
    public String queryTradeDetailListPage() {
        return "frontstage/_sales-tradeDetail-list";
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
