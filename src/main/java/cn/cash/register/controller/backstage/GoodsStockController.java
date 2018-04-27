/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.StockFlowQueryRequest;
import cn.cash.register.common.request.StockWarningQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsStockFlow;
import cn.cash.register.service.GoodsStockService;
import cn.cash.register.util.ResultSet;

/**
 * 商品库存相关Controller
 * @author HuHui
 * @version $Id: GoodsStockFlowController.java, v 0.1 2018年4月26日 上午11:45:00 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/stock")
public class GoodsStockController {

    @Resource
    private GoodsStockService stockService;

    /**
     * 查询商品库存变动明细
     */
    @ResponseBody
    @RequestMapping(value = "/queryStockFlow")
    public ResultSet queryStockFlow(StockFlowQueryRequest request) {
        List<GoodsStockFlow> list = stockService.query(request);
        return ResultSet.success().put("list", list);
    }

    /**
     * 查询库存预警的商品
     */
    @ResponseBody
    @RequestMapping(value = "/queryStockWarningGoods")
    public ResultSet queryStockWarningGoods(StockWarningQueryRequest request) {
        PageInfo<GoodsInfo> list = stockService.queryStockWarningGoods(request);
        return ResultSet.success().put("list", list);
    }

}