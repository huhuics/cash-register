/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.domain.ExchangeJobDetail;
import cn.cash.register.dao.domain.GoodsSaleStatistics;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.service.SalesService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.ResultSet;

/**
 * 销售相关接口的Controller
 * @author HuHui
 * @version $Id: SalesController.java, v 0.1 2018年5月3日 下午3:27:14 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/sales")
public class SalesController {

    @Resource
    private ExchangeJobService exchangeJobService;

    @Resource
    private SalesService       salesService;

    /**
     * 查询交接班记录
     */
    @ResponseBody
    @PostMapping(value = "/queryExchangeJobs")
    public ResultSet queryExchangeJobs(ExchangeJobQueryRequest request) {
        List<ExchangeJobDetail> exchangeJobs = exchangeJobService.query(request);
        return ResultSet.success().put("exchangeJobs", exchangeJobs);
    }

    /**
     * 查询营业概况
     */
    @ResponseBody
    @PostMapping(value = "/queryBasicFacts")
    public ResultSet queryBasicFacts(SalesBasicFactsQueryRequest request) {
        AssertUtil.assertNotNull(request, "查询参数不能为空");
        Map<String, SalesBasicFacts> basicFacts = salesService.queryBasicFacts(request);
        return ResultSet.success().put("basicFacts", basicFacts);
    }

    /**
     * 查询商品销售统计
     */
    @ResponseBody
    @PostMapping(value = "/queryGoodsSaleStatistics")
    public ResultSet queryGoodsSaleStatistics(TradeGoodsDetailQueryRequest request) {
        PageInfo<GoodsSaleStatistics> ret = salesService.queryGoodsSaleStatistics(request);
        return ResultSet.success().put("ret", ret);
    }

    /**
     * 查询交接班记录页面
     */
    @GetMapping(value = "/queryExchangeJobs")
    public String queryExchangeJobsPage() {
        return "backstage/_sales-exchangeJobs";
    }

    /**
     * 查询营业概况页面
     */
    @GetMapping(value = "/queryBasicFacts")
    public String queryBasicFactsPage() {
        return "backstage/_sales-basicFacts";
    }

    /**
     * 查询销售单据页面
     */
    @GetMapping(value = "/tradeDetail")
    public String salesTradeDetailList() {
        return "backstage/_sales-tradeDetail-list";
    }

}
