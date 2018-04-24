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

import cn.cash.register.common.request.CancelRequest;
import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.common.request.TradeGoodsDetailQueryRequest;
import cn.cash.register.common.request.TradeRequest;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.TradeGoodsDetailMapper;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.enums.TradeTypeEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.MemberService;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.LogUtil;
import cn.cash.register.util.Money;
import cn.cash.register.util.NumUtil;

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
    public boolean checkout(TradeRequest request) {
        LogUtil.info(logger, "收到收银请求");
        request.validate();

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                //订单流水号
                String tradeNo = NumUtil.getTradeNo();

                TradeDetail tradeDetail = new TradeDetail(tradeNo, new Date(), TradeTypeEnum.SALES.getCode());

                doTrade(tradeDetail, request);

                return true;
            }
        });
    }

    @Override
    public boolean refund(TradeRequest request) {
        LogUtil.info(logger, "收到退款请求");
        request.validate();

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                //订单流水号
                String tradeNo = NumUtil.getTradeNo();

                TradeDetail tradeDetail = new TradeDetail(tradeNo, new Date(), TradeTypeEnum.REFUND.getCode());

                doTrade(tradeDetail, request);

                return true;
            }
        });
    }

    @Override
    public boolean cancel(CancelRequest request) {
        LogUtil.info(logger, "收到反结账请求");
        request.validate();

        return txTemplate.execute(new TransactionCallback<Boolean>() {
            @Override
            public Boolean doInTransaction(TransactionStatus status) {

                doCancel(request);

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
     * 执行收银/退款操作
     */
    private void doTrade(TradeDetail tradeDetail, TradeRequest request) {
        //1.写trade_goods_detail表
        insertTradeGoodsDetail(tradeDetail, request);

        //2.写trade_detail表
        insertTradeDetail(tradeDetail, request);

        //3.积分变动
        memberService.updateIntegral(request.getMemberId(), tradeDetail.getTotalActualAmount());
    }

    /**
     * 收银写trade_goods_detail
     */
    private void insertTradeGoodsDetail(TradeDetail tradeDetail, TradeRequest request) {
        int goodsCount = 0; //商品数量
        Money totalAmount = new Money(); //商品原价之和
        int goodsDiscount = 100; //商品折扣
        Money totalActualAmount = new Money(); //商品实收之和
        Money profitAmount = new Money(); //商品利润之和

        for (GoodsItem item : request.getGoodsItems()) {

            GoodsInfo goodsInfo = goodsInfoService.queryById(item.getGoodsId());

            //创建trade_goods_detail对象
            TradeGoodsDetail tradeGoodsDetail = new TradeGoodsDetail();
            tradeGoodsDetail.setTradeNo(tradeDetail.getTradeNo());
            tradeGoodsDetail.setTradeTime(tradeDetail.getTradeTime());
            tradeGoodsDetail.setTradeType(tradeDetail.getTradeType());
            tradeGoodsDetail.setGoodsCount(item.getGoodsCount());

            if (goodsInfo == null) { //无码商品
                tradeGoodsDetail.setGoodsName(item.getGoodsName());
            } else {//有码商品
                tradeGoodsDetail.setGoodsName(goodsInfo.getGoodsName());
                tradeGoodsDetail.setGoodsBrand(goodsInfo.getGoodsBrand());
                tradeGoodsDetail.setBarCode(goodsInfo.getBarCode());
                tradeGoodsDetail.setProductNumber(goodsInfo.getProductNumber());
                tradeGoodsDetail.setGoodsColor(goodsInfo.getGoodsColor());
                tradeGoodsDetail.setGoodsSize(goodsInfo.getGoodsSize());
                tradeGoodsDetail.setGoodsTag(goodsInfo.getGoodsTag());
                tradeGoodsDetail.setCategoryName(goodsInfo.getCategoryName());
                tradeGoodsDetail.setSupplierName(goodsInfo.getSupplierName());

                //修改商品库存
                goodsInfoService.updateStock(goodsInfo.getId(), item.getGoodsCount());

            }

            tradeGoodsDetail.setTotalAmount(new Money(item.getTotalAmount()));
            tradeGoodsDetail.setGoodsDiscount(item.getGoodsDiscount());
            tradeGoodsDetail.setTotalActualAmount(new Money(item.getTotalActualAmount()));
            tradeGoodsDetail.setGmtCreate(new Date());

            //计算商品利润
            double profit = (item.getTotalActualAmount() * item.getGoodsDiscount()) / 100.0;
            tradeGoodsDetail.setProfitAmount(new Money(profit));

            tradeGoodsDetailMapper.insertSelective(tradeGoodsDetail);

            //计算统计类交易信息
            goodsCount += item.getGoodsCount();
            totalAmount.addTo(tradeGoodsDetail.getTotalAmount());
            goodsDiscount = item.getGoodsDiscount();
            totalActualAmount.addTo(tradeGoodsDetail.getTotalActualAmount());
            profitAmount.addTo(tradeGoodsDetail.getProfitAmount());
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
    private void insertTradeDetail(TradeDetail tradeDetail, TradeRequest request) {
        tradeDetail.setMemberName(request.getMemberName());
        tradeDetail.setSellerNo(request.getSellerNo());
        tradeDetail.setShopperNo(request.getShopperNo());
        tradeDetail.setGoodsDetail(JSON.toJSONString(request.getGoodsItems()));
        tradeDetail.setPayChenal(JSON.toJSONString(request.getPayChenals()));
        tradeDetail.setIsExchangeJob(false);
        tradeDetail.setGmtCreate(new Date());

        tradeDetailMapper.insertSelective(tradeDetail);
    }

    /**
     * 反结账
     * 1.库存变动
     * 2.积分变动
     * 3.删除trade_goods_detail
     * 4.删除trade_detail
     */
    private void doCancel(CancelRequest request) {
        for (GoodsItem item : request.getGoodsItems()) {
            Long goodsId = item.getGoodsId();
            if (goodsId != null) { //有码商品
                //商品库存变动
                goodsInfoService.updateStock(goodsId, item.getGoodsCount());
            }
        }

        //积分变动
        memberService.updateIntegral(request.getMemberId(), request.getTotalActualAmountMoney());

        tradeGoodsDetailMapper.deleteByTradeNo(request.getTradeNo());

        tradeDetailMapper.deleteByTradeNo(request.getTradeNo());
    }

}
