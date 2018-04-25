/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.GoodsTrafficQueryRequest;
import cn.cash.register.common.request.InTrafficRequest;
import cn.cash.register.common.request.OutTrafficRequest;
import cn.cash.register.dao.GoodsTrafficMapper;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsTraffic;
import cn.cash.register.enums.TrafficTypeEnum;
import cn.cash.register.service.GoodsInfoService;
import cn.cash.register.service.GoodsTrafficService;
import cn.cash.register.util.Money;
import cn.cash.register.util.NumUtil;

/**
 * 商品货流服务接口实现类
 * @author HuHui
 * @version $Id: GoodsTrafficServiceImpl.java, v 0.1 2018年4月25日 上午11:38:12 HuHui Exp $
 */
@Service
public class GoodsTrafficServiceImpl implements GoodsTrafficService {

    @Resource
    private GoodsTrafficMapper  trafficMapper;

    @Resource
    private GoodsInfoService    goodsInfoService;

    @Resource
    private TransactionTemplate txTemplate;

    @Override
    public Long addInTraffic(InTrafficRequest request) {
        GoodsTraffic traffic = convert(request);
        return txTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {

                //查询并修改商品信息
                GoodsInfo goodsInfo = goodsInfoService.queryById(request.getGoodsId());
                inUpdateGoodsInfo(goodsInfo, traffic);

                return trafficMapper.insertSelective(traffic);
            }
        });
    }

    @Override
    public Long addOutTraffic(OutTrafficRequest request) {
        GoodsTraffic traffic = convert(request);
        return txTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {

                //查询并修改商品信息
                GoodsInfo goodsInfo = goodsInfoService.queryById(request.getGoodsId());
                outUpdateGoodsInfo(goodsInfo, traffic);

                return trafficMapper.insertSelective(traffic);
            }
        });
    }

    @Override
    public GoodsTraffic queryById(Long id) {
        return trafficMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<GoodsTraffic> queryList(GoodsTrafficQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<GoodsTraffic> list = trafficMapper.list(request);

        return new PageInfo<GoodsTraffic>(list);
    }

    /**
     * 进货--修改商品信息属性值
     */
    private void inUpdateGoodsInfo(GoodsInfo goodsInfo, GoodsTraffic traffic) {
        //库存
        int inStock = goodsInfo.getGoodsStock() + traffic.getInCount();//实际进货库存
        int newTotalStock = inStock + traffic.getFreeCount();//实际总库存

        //新加权进货价 = ((原库存*原加权进货价)+(入库数*进货价))/实际进货库存
        Money newAvgPrice = ((goodsInfo.getAverageImportPrice().multiply(goodsInfo.getGoodsStock()))//
            .add((traffic.getInAmount().multiply(traffic.getInCount()))))//
                .divide(inStock);

        goodsInfo.setGoodsStock(newTotalStock);
        goodsInfo.setAverageImportPrice(newAvgPrice);
        goodsInfo.setLastImportPrice(traffic.getInAmount());

        goodsInfoService.update(goodsInfo);
    }

    /**
     * 出库--修改商品信息属性值
     */
    private void outUpdateGoodsInfo(GoodsInfo goodsInfo, GoodsTraffic traffic) {
        //库存
        int newTotalStock = goodsInfo.getGoodsStock() - traffic.getOutCount();

        //新加权进货价 = 旧加权进货价 - (变动库存*(出货价 - 旧加权进货价)) / (原库存 - 变动库存)
        Money newAvgPrice = goodsInfo.getAverageImportPrice().subtract((traffic.getOutAmount().subtract(goodsInfo.getAverageImportPrice())).multiply(traffic.getOutCount() / newTotalStock));

        goodsInfo.setGoodsStock(newTotalStock);
        goodsInfo.setAverageImportPrice(newAvgPrice);

        goodsInfoService.update(goodsInfo);
    }

    private GoodsTraffic convert(InTrafficRequest request) {
        GoodsTraffic traffic = new GoodsTraffic();
        traffic.setTrafficNo(NumUtil.getTrafficNo());
        traffic.setTrafficType(TrafficTypeEnum.IN.getCode());
        traffic.setStatus(request.getStatus());
        traffic.setGoodsName(request.getGoodsName());
        traffic.setBarCode(request.getBarCode());
        traffic.setGoodsColor(request.getGoodsColor());
        traffic.setGoodsSize(request.getGoodsSize());
        traffic.setSupplierName(request.getSupplierName());
        traffic.setGoodsStock(request.getGoodsStock());
        traffic.setInCount(request.getInCount());
        traffic.setInAmount(new Money(request.getInAmount()));
        traffic.setFreeCount(request.getFreeCount());
        traffic.setAdvancePaymentAmount(new Money(request.getAdvancePaymentAmount()));
        traffic.setQuantityUnit(request.getQuantityUnit());
        traffic.setTotalAmount(new Money(request.getTotalAmount()));
        traffic.setOperatorNo(request.getOperatorNo());
        traffic.setRemark(request.getRemark());
        traffic.setGmtCreate(new Date());

        return traffic;
    }

    private GoodsTraffic convert(OutTrafficRequest request) {
        GoodsTraffic traffic = new GoodsTraffic();
        traffic.setTrafficNo(NumUtil.getTrafficNo());
        traffic.setTrafficType(request.getTrafficType());
        traffic.setStatus(true);
        traffic.setGoodsName(request.getGoodsName());
        traffic.setBarCode(request.getBarCode());
        traffic.setGoodsColor(request.getGoodsColor());
        traffic.setGoodsSize(request.getGoodsSize());
        traffic.setSupplierName(request.getSupplierName());
        traffic.setGoodsStock(request.getGoodsStock());
        traffic.setQuantityUnit(request.getQuantityUnit());
        traffic.setOutPriceType(request.getOutPriceType());
        traffic.setOutAmount(new Money(request.getOutAmount()));
        traffic.setOutCount(request.getOutCount());
        traffic.setTotalAmount(new Money(request.getTotalAmount()));
        traffic.setOperatorNo(request.getOperatorNo());
        traffic.setRemark(request.getRemark());
        traffic.setGmtCreate(new Date());

        return traffic;
    }

}
