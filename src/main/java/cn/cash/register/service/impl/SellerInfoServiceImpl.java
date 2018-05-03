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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.Constants;
import cn.cash.register.common.request.AchievementQueryRequest;
import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.SellerInfoMapper;
import cn.cash.register.dao.TradeGoodsDetailMapper;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.dao.domain.TradeGoodsDetail;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.service.SystemParameterService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;

/**
 * 收银员信息服务接口实现类
 * @author HuHui
 * @version $Id: SellerInfoServiceImpl.java, v 0.1 2018年4月18日 下午5:33:56 HuHui Exp $
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    private static final Logger    logger = LoggerFactory.getLogger(SellerInfoServiceImpl.class);

    @Resource
    private SellerInfoMapper       sellerInfoMapper;

    @Resource
    private SystemParameterService parameterService;

    @Resource
    private TradeGoodsDetailMapper tradeGoodsDetailMapper;

    @Override
    public Long addSeller(SellerInfo sellerInfo) {
        LogUtil.info(logger, "收到增加收银员请求,sellerInfo={0}", sellerInfo);

        sellerInfo.setPartOfShop(parameterService.getByCode(Constants.SHOP_NAME).getParamValue());

        Date createTime = new Date();
        sellerInfo.setGmtCreate(createTime); // 创建时间

        return sellerInfoMapper.insertSelective(sellerInfo);
    }

    @Override
    public int update(SellerInfo sellerInfo) {
        LogUtil.info(logger, "收到修改收银员请求,id={0}", sellerInfo.getId());

        return sellerInfoMapper.updateByPrimaryKeySelective(sellerInfo);
    }

    @Override
    public int delete(Long id) {
        LogUtil.info(logger, "收到删除收银员请求,id={0}", id);
        return sellerInfoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<SellerInfo> queryList(SellerInfoQueryRequest request) {
        LogUtil.info(logger, "收到分页查询收银员请求,request={0}", request);
        request.validate();

        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<SellerInfo> list = sellerInfoMapper.list(request);

        return new PageInfo<SellerInfo>(list);
    }

    @Override
    public SellerInfo queryById(Long id) {
        LogUtil.info(logger, "收到查询收银员请求,id={0}", id);

        return sellerInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public SellerInfo queryBySellerNo(String sellerNo) {
        SellerInfo seller = sellerInfoMapper.selectBySellerNo(sellerNo);
        return seller;
    }

    @Override
    public SellerInfo login(String sellerNo, String password) {
        SellerInfo seller = queryBySellerNo(sellerNo);

        AssertUtil.assertNotNull(seller, "收银员不存在");
        AssertUtil.assertTrue(seller.getStatus(), "收银员被禁用");
        AssertUtil.assertTrue(StringUtils.equals(password, seller.getPassword()), "用户名或密码错误");

        return seller;
    }

    @Override
    public PageInfo<TradeGoodsDetail> queryAchievement(AchievementQueryRequest request) {
        request.validate();
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());

        List<TradeGoodsDetail> list = tradeGoodsDetailMapper.selectSellerAchievement(request);
        return new PageInfo<TradeGoodsDetail>(list);
    }

}
