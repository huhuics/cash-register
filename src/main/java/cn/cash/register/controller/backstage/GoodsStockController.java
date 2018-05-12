/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsInfoQueryRequest;
import cn.cash.register.common.request.StockFlowQueryRequest;
import cn.cash.register.common.request.StockWarningQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsStockFlow;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsStockService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 商品库存相关Controller
 * @author HuHui
 * @version $Id: GoodsStockFlowController.java, v 0.1 2018年4月26日 上午11:45:00 HuHui Exp $
 */
@Controller
@RequestMapping("/admin/stock")
public class GoodsStockController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsStockController.class);

    @Resource
    private GoodsStockService   stockService;

    @Resource
    private GoodsInfoService    goodsInfoService;

    /**
     * 跳转到库存查询页
     */
    @GetMapping
    public String list() {
        return "backstage/_stock-list";
    }

    /**
     * 查询商品库存资料列表
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    public ResultSet queryGoodsInfoList(GoodsInfoQueryRequest request) {
        PageInfo<GoodsInfo> queryList = goodsInfoService.queryList(request);
        return ResultSet.success().put("page", queryList);
    }

    /**
     * 跳转到库存变动明细页
     */
    @GetMapping(value = "/flow")
    public String flowList() {
        return "backstage/_stock-flow-list";
    }

    /**
     * 跳转到盘点历史页
     */
    @GetMapping(value = "/check")
    public String checkPage() {
        return "backstage/_stock-check-list";
    }

    /**
     * 查询库存变动明细
     */
    @ResponseBody
    @RequestMapping(value = "/flow/queryList")
    public ResultSet queryStockFlow(StockFlowQueryRequest request) {
        LogUtil.info(logger, "[Controller]接收到查询库存变动明细请求,request={0}", request);

        List<GoodsStockFlow> list = stockService.query(request);

        LogUtil.info(logger, "[Controller]查询库存变动明细结果,list={0}", list);
        return ResultSet.success().put("list", list);
    }

    /**
     * 跳转到库存预警页
     */
    @GetMapping(value = "/warning")
    public String warningList() {
        return "backstage/_stock-warning-list";
    }

    /**
     * 查询库存预警的商品
     */
    @ResponseBody
    @RequestMapping(value = "/warning/queryGoods")
    public ResultSet queryStockWarningGoods(StockWarningQueryRequest request) {
        PageInfo<GoodsInfo> list = stockService.queryStockWarningGoods(request);
        return ResultSet.success().put("page", list);
    }

}
