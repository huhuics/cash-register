/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.frontstage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.CancelRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.common.request.TradeRequest;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.MemberInfo;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.MemberService;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.AssertUtil;
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
    private TradeService     tradeService;

    @Resource
    private GoodsInfoService goodsInfoService;

    @Resource
    private MemberService    memberService;

    /**
     * 根据关键字搜索并添加商品
     * @param keyword 条码/拼音码/商品名
     * @return  商品信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchGoodsInfo")
    public ResultSet searchGoodsInfo(String keyword) {
        AssertUtil.assertNotBlank(keyword, "查询关键字不能为空");
        List<GoodsInfo> goodsInfos = goodsInfoService.search(keyword);
        if (CollectionUtils.isEmpty(goodsInfos)) {
            return ResultSet.success().put("size", 0);
        } else if (goodsInfos.size() == 1) {
            return ResultSet.success().put("size", 1).put("goods", goodsInfos.get(0));
        } else {
            return ResultSet.success().put("size", goodsInfos.size()).put("goodsInfos", goodsInfos);
        }
    }

    /**
     * 根据关键字搜索会员
     * @param keyword 会员号/姓名/手机号
     * @return  会员信息列表
     */
    @ResponseBody
    @RequestMapping(value = "/searchMemberInfo")
    public ResultSet searchMemberInfo(String keyword) {
        AssertUtil.assertNotBlank(keyword, "关键字不能为空");
        List<MemberInfo> memberInfos = memberService.search(keyword);
        if (CollectionUtils.isEmpty(memberInfos)) {
            return ResultSet.success().put("size", 0);
        } else if (memberInfos.size() == 1) {
            return ResultSet.success().put("size", 1).put("member", memberInfos.get(0));
        } else {
            return ResultSet.success().put("size", memberInfos.size()).put("memberInfos", memberInfos);
        }
    }

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
