/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.GoodsStockCheckQueryRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsStockCheck;
import cn.cash.register.dao.domain.GoodsStockCheckDetail;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsStockCheckServie;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.ResultSet;

/**
 * 盘点--收银端Controller
 * @author HuHui
 * @version $Id: GoodsStockCheckController.java, v 0.1 2018年5月9日 下午9:38:00 HuHui Exp $
 */
@Controller
@RequestMapping(value = "/cashier/stockCheck")
public class GoodsStockCheckController {
    private static final Logger   logger = LoggerFactory.getLogger(GoodsStockCheckController.class);

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
     * 增加盘点记录
     */
    @ResponseBody
    @PostMapping(value = "addCheck")
    public ResultSet addCheck(String remark, String detailsStr, HttpSession session) {
        LogUtil.info(logger, "[Controller]收到#增加盘点记录#请求,detailsStr={0},remark={1}", detailsStr, remark);
        SellerInfo seller = (SellerInfo) session.getAttribute(Constants.LOGIN_FLAG_SELLER);
        Long ret = checkService.addCheck(seller.getSellerNo(), remark, detailsStr);
        return ResultSet.success().put("ret", ret);
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

    /**
     * 查询商品总数
     */
    @ResponseBody
    @GetMapping(value = "queryGoodsCount")
    public ResultSet queryGoodsCount() {
        int goodsCount = goodsInfoService.queryGoodsCount();
        return ResultSet.success().put("goodsCount", goodsCount);
    }

}
