/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.ShopperInfoQueryRequest;
import cn.cash.register.dao.ShopperInfoMapper;
import cn.cash.register.dao.TradeGoodsDetailMapper;
import cn.cash.register.dao.domain.ShopperInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.ShopperInfoService;

/**
 * 导购员服务接口实现类
 * @author HuHui
 * @version $Id: ShopperInfoServiceImpl.java, v 0.1 2018年5月3日 下午2:27:53 HuHui Exp $
 */
@Service
public class ShopperInfoServiceImpl implements ShopperInfoService {

    @Resource
    private ShopperInfoMapper      shopperInfoMapper;

    @Resource
    private TradeGoodsDetailMapper tradeGoodsDetailMapper;

    @Override
    public Long add(ShopperInfo info) {
        info.setGmtCreate(new Date());
        return shopperInfoMapper.insertSelective(info);
    }

    @Override
    public int delete(Long id) {
        return shopperInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(ShopperInfo info) {
        return shopperInfoMapper.updateByPrimaryKeySelective(info);
    }

    @Override
    public ShopperInfo queryById(Long id) {
        return shopperInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ShopperInfo> queryAll(ShopperInfoQueryRequest request) {
        return shopperInfoMapper.list(request);
    }

    @Override
    public PageInfo<ShopperInfo> queryPage(ShopperInfoQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<ShopperInfo> list = shopperInfoMapper.list(request);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<TradeGoodsDetail> queryAchievement(AchievementQueryRequest request) {
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<TradeGoodsDetail> list = tradeGoodsDetailMapper.selectShopperAchievement(request);

        return new PageInfo<>(list);
    }

}
