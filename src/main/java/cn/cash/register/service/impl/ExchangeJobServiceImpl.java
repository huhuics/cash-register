/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;

import cn.cash.register.common.request.ExchangeJobQueryRequest;
import cn.cash.register.common.request.ExchangeJobTradeDetailRequest;
import cn.cash.register.dao.ExchangeJobDetailMapper;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.domain.ExchangeJobDetail;
import cn.cash.register.dao.domain.PayChenal;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.enums.PayChenalEnum;
import cn.cash.register.service.ExchangeJobService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;

/**
 * 交接班服务接口实现类
 * @author HuHui
 * @version $Id: ExchangeJobServiceImpl.java, v 0.1 2018年5月2日 下午7:41:30 HuHui Exp $
 */
@Service
public class ExchangeJobServiceImpl implements ExchangeJobService {

    private static final Logger     logger = LoggerFactory.getLogger(ExchangeJobServiceImpl.class);

    @Resource
    private ExchangeJobDetailMapper exchangeJobDetailMapper;

    @Resource
    private TradeDetailMapper       tradeDetailMapper;

    @Resource
    private TransactionTemplate     txTemplate;

    @Override
    public Long create(String sellerNo) {
        //根据收银员编号查询是否有未完成交接班的记录
        ExchangeJobDetail unfinishedJob = exchangeJobDetailMapper.selectUnfinished(sellerNo);
        if (unfinishedJob != null) {
            LogUtil.info(logger, "收银员{0}存在未完成交接班记录", sellerNo);
            return unfinishedJob.getId();
        }

        //不存在未完成交接班记录,则创建新的未完成记录
        LogUtil.info(logger, "创建新的未完成交接班记录,sellerNo={0}", sellerNo);
        ExchangeJobDetail detail = new ExchangeJobDetail(new Date(), sellerNo, new Date());

        return exchangeJobDetailMapper.insertSelective(detail);
    }

    @Override
    public int update(ExchangeJobDetail detail) {
        return exchangeJobDetailMapper.updateByPrimaryKeySelective(detail);
    }

    @Override
    public boolean exchangeJob(Long exchangeJobId) {
        ExchangeJobDetail unfinishedJob = exchangeJobDetailMapper.selectByPrimaryKey(exchangeJobId);
        if (unfinishedJob == null) {
            LogUtil.info(logger, "收银员已完成交接班,不需重复交接");
            return true;
        }

        //查询需要交接班的交易详情
        ExchangeJobTradeDetailRequest request = new ExchangeJobTradeDetailRequest(unfinishedJob.getStartTime(), exchangeJobId, unfinishedJob.getSellerNo());
        List<TradeDetail> tradeDetails = tradeDetailMapper.selectExchangeJobTradeDetails(request);

        Money checkoutTotalAmount = new Money();
        Money cashAmount = new Money();
        Money balanceAmount = new Money();
        Money unionpayAmount = new Money();
        Money alipayAmount = new Money();
        Money wcpayAmount = new Money();

        for (TradeDetail tradeDetail : tradeDetails) {
            checkoutTotalAmount.addTo(tradeDetail.getTotalActualAmount());
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

        unfinishedJob.setEndTime(new Date());
        unfinishedJob.setCheckoutTotalAmount(checkoutTotalAmount);
        unfinishedJob.setCashAmount(cashAmount);
        unfinishedJob.setBalanceAmount(balanceAmount);
        unfinishedJob.setUnionpayAmount(unionpayAmount);
        unfinishedJob.setAlipayAmount(alipayAmount);
        unfinishedJob.setWcpayAmount(wcpayAmount);
        unfinishedJob.setIsfinished(true);

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                for (TradeDetail tradeDetail : tradeDetails) {
                    tradeDetail.setIsExchangeJob(true); //将交易详情设置为已交接班
                    tradeDetailMapper.updateByPrimaryKeySelective(tradeDetail);
                }

                exchangeJobDetailMapper.updateByPrimaryKeySelective(unfinishedJob);

                return true;
            }
        });

    }

    @Override
    public List<ExchangeJobDetail> query(ExchangeJobQueryRequest request) {
        request.validate();
        return exchangeJobDetailMapper.list(request);
    }

}
