/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.MemberRechargeDetailMapper;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.TradeGoodsDetailMapper;
import cn.cash.register.dao.domain.GoodsSaleStatistics;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.dao.domain.PayChenal;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.enums.PayChenalEnum;
import cn.cash.register.enums.SalesBasicFactsEnum;
import cn.cash.register.service.SalesService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.Money;

/**
 * 销售服务接口实现类
 * @author HuHui
 * @version $Id: SalesServiceImpl.java, v 0.1 2018年5月10日 下午9:13:52 HuHui Exp $
 */
@Service
public class SalesServiceImpl implements SalesService {

    @Resource
    private TradeDetailMapper          tradeDetailMapper;

    @Resource
    private TradeGoodsDetailMapper     tradeGoodsDetailMapper;

    @Resource
    private MemberRechargeDetailMapper rechargeDetailMapper;

    @Override
    public Map<String, SalesBasicFacts> queryBasicFacts(SalesBasicFactsQueryRequest request) {
        request.validate();
        Map<String, SalesBasicFacts> factsMap = new HashMap<>();

        List<TradeDetail> tradeDetails = tradeDetailMapper.queryByTradeTime(request);

        //1.统计商品销售信息
        SalesBasicFacts goodsSalesFact = new SalesBasicFacts();
        Money checkoutTotalAmount = new Money();
        Money profitAmount = new Money();
        Money goodsCashAmount = new Money();
        Money goodsBalanceAmount = new Money();
        Money goodsUnionpayAmount = new Money();
        Money goodsAlipayAmount = new Money();
        Money goodsWcpayAmount = new Money();

        for (TradeDetail tradeDetail : tradeDetails) {
            checkoutTotalAmount.addTo(tradeDetail.getTotalActualAmount());
            profitAmount.addTo(tradeDetail.getProfitAmount());
            if (StringUtils.isNotBlank(tradeDetail.getPayChenal())) {
                List<PayChenal> chenals = JSON.parseArray(tradeDetail.getPayChenal(), PayChenal.class);
                for (PayChenal chenal : chenals) {

                    PayChenalEnum chenalEnum = PayChenalEnum.valueOf(chenal.getChenal());
                    Money cheanlAmount = new Money(chenal.getAmount());

                    if (chenalEnum == PayChenalEnum.cash) {
                        goodsCashAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.balance) {
                        goodsBalanceAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.unionpay) {
                        goodsUnionpayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.alipay) {
                        goodsAlipayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.wcpay) {
                        goodsWcpayAmount.addTo(cheanlAmount);
                    }
                }
            }
        }

        String goodsSalesBasicFacts = "销售额：" + checkoutTotalAmount.getAmount() + ", 利润：" + profitAmount.getAmount() + ", 单数：" + (tradeDetails == null ? 0 : tradeDetails.size()) + ", 单均价："
                                      + ((tradeDetails == null || tradeDetails.size() == 0) ? 0 : checkoutTotalAmount.divide(tradeDetails.size()).getAmount());

        goodsSalesFact.setBasicFacts(goodsSalesBasicFacts);
        goodsSalesFact.setCash(goodsCashAmount);
        goodsSalesFact.setBalance(goodsBalanceAmount);
        goodsSalesFact.setUnionpay(goodsUnionpayAmount);
        goodsSalesFact.setAlipay(goodsAlipayAmount);
        goodsSalesFact.setWcpay(goodsWcpayAmount);

        factsMap.put(SalesBasicFactsEnum.goods_sales.name(), goodsSalesFact);

        //2.统计储值卡充值数据
        SalesBasicFacts balanceSalesFact = new SalesBasicFacts();
        Money balanceCashAmount = new Money();
        Money balanceUnionpayAmount = new Money();
        Money balanceAlipayAmount = new Money();
        Money balanceWcpayAmount = new Money();
        List<MemberRechargeDetail> rechargeDetails = rechargeDetailMapper.queryByTime(request);
        Money totalRechargeAmount = new Money();
        Money totalDonationAmount = new Money();
        for (MemberRechargeDetail rechargeDetail : rechargeDetails) {
            totalRechargeAmount.addTo(rechargeDetail.getRechargeAmount());
            totalDonationAmount.addTo(rechargeDetail.getDonationAmount());
            if (StringUtils.isNotBlank(rechargeDetail.getPayChenal())) {
                List<PayChenal> chenals = JSON.parseArray(rechargeDetail.getPayChenal(), PayChenal.class);
                for (PayChenal chenal : chenals) {

                    PayChenalEnum chenalEnum = PayChenalEnum.valueOf(chenal.getChenal());
                    Money cheanlAmount = new Money(chenal.getAmount());

                    if (chenalEnum == PayChenalEnum.cash) {
                        balanceCashAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.unionpay) {
                        balanceUnionpayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.alipay) {
                        balanceAlipayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.wcpay) {
                        balanceWcpayAmount.addTo(cheanlAmount);
                    }
                }
            }
        }

        String balanceSalesBasicFacts = "充值：" + totalRechargeAmount.getAmount() + ", 赠送：" + totalDonationAmount.getAmount() + ", 单数：" + (rechargeDetails == null ? 0 : rechargeDetails.size());
        balanceSalesFact.setBasicFacts(balanceSalesBasicFacts);
        balanceSalesFact.setCash(balanceCashAmount);
        balanceSalesFact.setUnionpay(balanceUnionpayAmount);
        balanceSalesFact.setAlipay(balanceAlipayAmount);
        balanceSalesFact.setWcpay(balanceWcpayAmount);

        factsMap.put(SalesBasicFactsEnum.balance.name(), balanceSalesFact);

        return factsMap;
    }

    @Override
    public PageInfo<GoodsSaleStatistics> queryGoodsSaleStatistics(TradeGoodsDetailQueryRequest request) {
        AssertUtil.assertNotNull(request, "参数不能为空");
        request.validate();

        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<GoodsSaleStatistics> statisticses = tradeGoodsDetailMapper.querySaleStatistics(request);

        return new PageInfo<>(statisticses);
    }

}
