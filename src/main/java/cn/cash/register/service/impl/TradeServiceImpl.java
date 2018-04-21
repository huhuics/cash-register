/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.TradeDetailQueryRequest;
import cn.cash.register.dao.TradeDetailMapper;
import cn.cash.register.dao.domain.TradeDetail;
import cn.cash.register.service.TradeService;
import cn.cash.register.util.LogUtil;

/**
 * 交易服务接口实现类
 * @author HuHui
 * @version $Id: TradeServiceImpl.java, v 0.1 2018年4月21日 上午9:57:20 HuHui Exp $
 */
@Service
public class TradeServiceImpl implements TradeService {

    private static final Logger logger = LoggerFactory.getLogger(TradeServiceImpl.class);

    @Resource
    private TradeDetailMapper   detailMapper;

    @Override
    public PageInfo<TradeDetail> queryTradeDetailList(TradeDetailQueryRequest request) {
        LogUtil.info(logger, "收到分页查询销售单据请求");
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<TradeDetail> list = detailMapper.list(request);

        return new PageInfo<TradeDetail>(list);
    }

}
