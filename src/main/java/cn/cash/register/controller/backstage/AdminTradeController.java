/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.controller.backstage;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
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
 * 后端交易服务Controller
 * 
 * @author 51
 * @version $Id: AdminTradeController.java, v 0.1 2018年5月22日 上午10:24:55 51 Exp $
 */
@Controller
@RequestMapping("/admin/trade")
public class AdminTradeController {

    private static final Logger logger = LoggerFactory.getLogger(AdminTradeController.class);

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

        ResultSet resultSet = ResultSet.success().put("goodsInfos", goodsInfos);

        if (CollectionUtils.isEmpty(goodsInfos)) {
            return resultSet.put("size", 0);
        } else {
            return resultSet.put("size", goodsInfos.size());
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

        ResultSet resultSet = ResultSet.success().put("memberInfos", memberInfos);

        if (CollectionUtils.isEmpty(memberInfos)) {
            return resultSet.put("size", 0);
        } else {
            return resultSet.put("size", memberInfos.size());
        }
    }

}
