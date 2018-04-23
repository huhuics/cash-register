/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.CheckoutRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.TradeGoodsDetailMapper;
import cn.cash.register.dao.domain.CheckoutGoodsItem;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.enums.TradeTypeEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.MemberService;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;

/**
 * 交易服务接口实现类
 * @author HuHui
 * @version $Id: TradeServiceImpl.java, v 0.1 2018年4月21日 上午9:57:20 HuHui Exp $
 */
@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger    logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Resource
    private TradeDetailMapper      tradeDetailMapper;

    @Resource
    private TradeGoodsDetailMapper tradeGoodsDetailMapper;

    @Resource
    private MemberService          memberService;

    @Resource
    private TransactionTemplate    txTemplate;

    @Resource
    private GoodsInfoService       goodsInfoService;

    @Override
    public boolean checkout(CheckoutRequest request) {
        LogUtil.info(logger, "收到收银请求");
        request.validate();

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                //订单流水号
                String tradeNo = DateUtil.format(new Date(), DateUtil.msecFormat);

                TradeDetail tradeDetail = new TradeDetail(tradeNo, new Date(), TradeTypeEnum.SALES.getCode());

                //1.写trade_goods_detail表
                insertTradeGoodsDetail(tradeDetail, request.getGoodsItems());

                //2.写trade_detail表
                insertTradeDetail(tradeDetail, request);

                return true;
            }
        });

    }

    @Override
    public PageInfo<TradeDetail> queryTradeDetailList(TradeDetailQueryRequest request) {
        LogUtil.info(logger, "收到分页查询销售单据请求");
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<TradeDetail> list = tradeDetailMapper.list(request);

        return new PageInfo<TradeDetail>(list);
    }

    @Override
    public PageInfo<TradeGoodsDetail> queryTradeGoodsDetailList(TradeGoodsDetailQueryRequest request) {
        LogUtil.info(logger, "收到分页查询销售商品明细请求");
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<TradeGoodsDetail> list = tradeGoodsDetailMapper.list(request);

        return new PageInfo<TradeGoodsDetail>(list);
    }

    /**
     * 收银写trade_goods_detail
     */
    private void insertTradeGoodsDetail(TradeDetail tradeDetail, List<CheckoutGoodsItem> items) {
        int goodsCount = 0; //商品数量
        Money totalAmount = new Money(); //商品原价之和
        int goodsDiscount = 100; //商品折扣
        Money totalActualAmount = new Money(); //商品实收之和
        Money profitAmount = new Money(); //商品利润之和

        for (CheckoutGoodsItem item : items) {

            GoodsInfo goodsInfo = goodsInfoService.queryById(item.getGoodsId());

            //创建trade_goods_detail对象
            TradeGoodsDetail detail = new TradeGoodsDetail();
            detail.setTradeNo(tradeDetail.getTradeNo());
            detail.setTradeTime(tradeDetail.getTradeTime());
            detail.setTradeType(tradeDetail.getTradeType());
            detail.setGoodsName(goodsInfo.getGoodsName());
            detail.setGoodsBrand(goodsInfo.getGoodsBrand());
            detail.setBarCode(goodsInfo.getBarCode());
            detail.setProductNumber(goodsInfo.getProductNumber());
            detail.setGoodsColor(goodsInfo.getGoodsColor());
            detail.setGoodsSize(goodsInfo.getGoodsSize());
            detail.setGoodsCount(item.getGoodsCount());
            detail.setGoodsTag(goodsInfo.getGoodsTag());
            detail.setCategoryName(goodsInfo.getCategoryName());
            detail.setSupplierName(goodsInfo.getSupplierName());
            detail.setTotalAmount(new Money(item.getTotalAmount()));
            detail.setGoodsDiscount(item.getGoodsDiscount());
            detail.setTotalActualAmount(new Money(item.getTotalActualAmount()));
            detail.setProfitAmount(new Money(item.getProfitAmount()));
            detail.setGmtCreate(new Date());

            tradeGoodsDetailMapper.insertSelective(detail);

            //计算统计类交易信息
            goodsCount += item.getGoodsCount();
            totalAmount.addTo(detail.getTotalAmount());
            goodsDiscount = item.getGoodsDiscount();
            totalActualAmount.addTo(detail.getTotalActualAmount());
            profitAmount.addTo(detail.getProfitAmount());
        }

        tradeDetail.setGoodsCount(goodsCount);
        tradeDetail.setTotalAmount(totalAmount);
        tradeDetail.setGoodsDiscount(goodsDiscount);
        tradeDetail.setTotalActualAmount(totalActualAmount);
        tradeDetail.setProfitAmount(profitAmount);
    }

    /**
     * 收银写trade_detail
     */
    private void insertTradeDetail(TradeDetail tradeDetail, CheckoutRequest request) {
        tradeDetail.setMemberName(request.getMemberName());
        tradeDetail.setSellerNo(request.getSellerNo());
        tradeDetail.setShopperNo(request.getShopperNo());
        tradeDetail.setGoodsDetail(JSON.toJSONString(request.getGoodsItems()));
        tradeDetail.setPayChenal(JSON.toJSONString(request.getPayChenals()));
        tradeDetail.setIsExchangeJob(false);
        tradeDetail.setGmtCreate(new Date());

        tradeDetailMapper.insertSelective(tradeDetail);
    }

}
