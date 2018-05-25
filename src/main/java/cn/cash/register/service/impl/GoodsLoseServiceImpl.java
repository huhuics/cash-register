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

import com.alibaba.fastjson.JSON;

import cn.cash.register.common.request.GoodsLoseInfoAddRequest;
import cn.cash.register.common.request.GoodsLoseInfoQueryRequest;
import cn.cash.register.dao.GoodsLoseInfoMapper;
import cn.cash.register.dao.GoodsLoseReasonMapper;
import cn.cash.register.dao.domain.GoodsLoseInfo;
import cn.cash.register.dao.domain.GoodsLoseItem;
import cn.cash.register.dao.domain.GoodsLoseReason;
import cn.cash.register.service.GoodsLoseService;
import cn.cash.register.util.Money;

/**
 * 商品报损服务接口实现类
 * @author HuHui
 * @version $Id: GoodsLoseServiceImpl.java, v 0.1 2018年4月26日 下午7:02:43 HuHui Exp $
 */
@Service
public class GoodsLoseServiceImpl implements GoodsLoseService {

    @Resource
    private GoodsLoseReasonMapper reasonMapper;

    @Resource
    private GoodsLoseInfoMapper   infoMapper;

    @Override
    public Long addLoseReason(String reason) {
        GoodsLoseReason loseReason = new GoodsLoseReason(reason);
        return reasonMapper.insertSelective(loseReason);
    }

    @Override
    public int deleteLoseReason(Long id) {
        return reasonMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateLoseReason(GoodsLoseReason reason) {
        return reasonMapper.updateByPrimaryKeySelective(reason);
    }

    @Override
    public List<GoodsLoseReason> queryAllReason() {
        return reasonMapper.queryAll();
    }

    @Override
    public Long addLoseInfo(GoodsLoseInfoAddRequest request) {
        if (request == null || StringUtils.isBlank(request.getLoseItemsStr())) {
            throw new RuntimeException("报损商品不能为空");
        }
        GoodsLoseInfo loseInfo = convert(request);
        return infoMapper.insertSelective(loseInfo);
    }

    @Override
    public List<GoodsLoseInfo> queryAllLoseInfo(GoodsLoseInfoQueryRequest request) {
        return infoMapper.query(request);
    }

    private GoodsLoseInfo convert(GoodsLoseInfoAddRequest request) {
        GoodsLoseInfo loseInfo = new GoodsLoseInfo();
        loseInfo.setShopName(request.getShopName());
        loseInfo.setGoodsDetail(request.getLoseItemsStr());

        List<GoodsLoseItem> goodsLoseItems = JSON.parseArray(request.getLoseItemsStr(), GoodsLoseItem.class);

        Money totalLoseAmount = new Money();
        for (GoodsLoseItem item : goodsLoseItems) {
            totalLoseAmount.addTo(new Money(item.getLoseAmount()));
        }

        loseInfo.setTotalLoseAmount(totalLoseAmount);
        loseInfo.setTurnoverPercent(request.getTurnoverPercent());
        loseInfo.setOperatorNo(request.getOperatorNo());
        loseInfo.setRemark(request.getRemark());
        loseInfo.setGmtCreate(new Date());

        return loseInfo;
    }

}
