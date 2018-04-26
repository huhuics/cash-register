/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.StockFlowQueryRequest;
import cn.cash.register.common.request.StockWarningQueryRequest;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.GoodsStockFlowMapper;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.GoodsStockFlow;
import cn.cash.register.enums.StockFlowTypeEnum;
import cn.cash.register.service.GoodsStockService;

/**
 * 商品库存服务接口实现类
 * @author HuHui
 * @version $Id: GoodsStockServiceImpl.java, v 0.1 2018年4月26日 上午10:28:18 HuHui Exp $
 */
@Service
public class GoodsStockServiceImpl implements GoodsStockService {

    @Resource
    private GoodsStockFlowMapper flowMapper;

    @Resource
    private GoodsInfoMapper      infoMapper;

    @Override
    public int record(String goodsName, String barCode, StockFlowTypeEnum type, int flowCount, String outBizNo) {

        GoodsStockFlow flow = new GoodsStockFlow();
        flow.setGoodsName(goodsName);
        flow.setBarCode(barCode);
        flow.setFlowType(type.getCode());
        flow.setFlowCount(flowCount);
        flow.setOutBizNo(outBizNo);
        flow.setGmtCreate(new Date());

        return flowMapper.insertSelective(flow);
    }

    @Override
    public List<GoodsStockFlow> query(StockFlowQueryRequest request) {

        if (StringUtils.isBlank(request.getBarCode()) || StringUtils.isBlank(request.getGoodsName())) {
            return null;
        }

        List<GoodsStockFlow> flows = flowMapper.list(request);
        return flows;
    }

    @Override
    public PageInfo<GoodsInfo> queryStockWarningGoods(StockWarningQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<GoodsInfo> list = infoMapper.queryByStock(request);

        return new PageInfo<GoodsInfo>(list);
    }

}
