/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.request.GoodsStockCheckQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsStockCheck;
import cn.cash.register.dao.domain.GoodsStockCheckDetail;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsStockCheckServie;
import cn.cash.register.util.ResultSet;

/**
 * 盘点--收银端Controller
 * @author HuHui
 * @version $Id: GoodsStockCheckController.java, v 0.1 2018年5月9日 下午9:38:00 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/admin/stockCheck")
public class GoodsStockCheckController {

    @Resource
    private GoodsInfoService      goodsInfoService;

    @Resource
    private GoodsStockCheckServie checkService;

    /**
     * 查询所有商品信息
     */
    @ResponseBody
    @GetMapping(value = "queryAllGoodsInfo")
    public ResultSet queryAllGoodsInfo() {
        List<GoodsInfo> goodsInfos = goodsInfoService.queryAll();
        return ResultSet.success().put("goodsInfos", goodsInfos);
    }

    /**
     * 盘点记录查询
     */
    @ResponseBody
    @PostMapping(value = "/queryCheck")
    public ResultSet queryCheck(GoodsStockCheckQueryRequest request) {
        List<GoodsStockCheck> checks = checkService.queryCheck(request);
        return ResultSet.success().put("checks", checks);
    }

    /**
     * 根据盘点记录查询盘点商品明细
     */
    @ResponseBody
    @PostMapping(value = "queryCheckDetail")
    public ResultSet queryCheckDetail(Long stockCheckId) {
        List<GoodsStockCheckDetail> details = checkService.queryCheckDetail(stockCheckId);
        return ResultSet.success().put("details", details);
    }

}
