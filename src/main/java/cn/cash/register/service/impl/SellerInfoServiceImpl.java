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

import cn.cash.register.common.request.SellerInfoQueryRequest;
import cn.cash.register.dao.SellerInfoMapper;
import cn.cash.register.dao.domain.SellerInfo;
import cn.cash.register.service.SellerInfoService;
import cn.cash.register.util.LogUtil;

/**
 * 收银员信息服务接口实现类
 * @author HuHui
 * @version $Id: SellerInfoServiceImpl.java, v 0.1 2018年4月18日 下午5:33:56 HuHui Exp $
 */
@Service
public class SellerInfoServiceImpl implements SellerInfoService {

    private static final Logger logger = LoggerFactory.getLogger(SellerInfoServiceImpl.class);

    @Resource
    private SellerInfoMapper    sellerInfoMapper;

    @Override
    public int addSeller(SellerInfo sellerInfo) {
        LogUtil.info(logger, "收到增加收银员请求,sellerInfo={0}", sellerInfo);

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

        return new PageInfo<>(list);
    }

    @Override
    public SellerInfo queryById(Long id) {
        LogUtil.info(logger, "收到查询收银员请求,id={0}", id);

        return sellerInfoMapper.selectByPrimaryKey(id);
    }

}
