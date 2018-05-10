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

import cn.cash.register.common.request.SalesBasicFactsQueryRequest;
import cn.cash.register.dao.MemberRechargeDetailMapper;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.domain.MemberRechargeDetail;
import cn.cash.register.dao.domain.PayChenal;
import cn.cash.register.dao.domain.SalesBasicFacts;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.enums.PayChenalEnum;
import cn.cash.register.enums.SalesBasicFactsEnum;
import cn.cash.register.service.SalesService;
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
        Money cashAmount = new Money();
        Money balanceAmount = new Money();
        Money unionpayAmount = new Money();
        Money alipayAmount = new Money();
        Money wcpayAmount = new Money();

        for (TradeDetail tradeDetail : tradeDetails) {
            checkoutTotalAmount.addTo(tradeDetail.getTotalActualAmount());
            profitAmount.addTo(tradeDetail.getProfitAmount());
            if (StringUtils.isNotBlank(tradeDetail.getPayChenal())) {
                List<PayChenal> chenals = JSON.parseArray(tradeDetail.getPayChenal(), PayChenal.class);
                for (PayChenal chenal : chenals) {

                    PayChenalEnum chenalEnum = PayChenalEnum.valueOf(chenal.getChenal());
                    Money cheanlAmount = new Money(chenal.getAmount());

                    if (chenalEnum == PayChenalEnum.cash) {
                        cashAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.unionpay) {
                        balanceAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.unionpay) {
                        unionpayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.alipay) {
                        alipayAmount.addTo(cheanlAmount);
                    } else if (chenalEnum == PayChenalEnum.wcpay) {
                        wcpayAmount.addTo(cheanlAmount);
                    }
                }
            }
        }

        String goodsSalesBasicFacts = "销售额：" + checkoutTotalAmount.getAmount() + ", 利润：" + profitAmount.getAmount() + ", 单数：" + tradeDetails.size();

        goodsSalesFact.setBasicFacts(goodsSalesBasicFacts);
        goodsSalesFact.setCash(cashAmount);
        goodsSalesFact.setBalance(balanceAmount);
        goodsSalesFact.setUnionpay(unionpayAmount);
        goodsSalesFact.setAlipay(alipayAmount);
        goodsSalesFact.setWcpay(wcpayAmount);

        factsMap.put(SalesBasicFactsEnum.goods_sales.name(), goodsSalesFact);

        //2.统计储值卡充值数据
        SalesBasicFacts balanceSalesFact = new SalesBasicFacts();
        List<MemberRechargeDetail> rechargeDetails = rechargeDetailMapper.queryByTime(request);
        Money totalRechargeAmount = new Money();
        Money totalDonationAmount = new Money();
        for (MemberRechargeDetail rechargeDetail : rechargeDetails) {

        }

        return factsMap;
    }

}
