/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cash.register.dao.GoodsSizeMapper;
import cn.cash.register.dao.domain.GoodsSize;
import cn.cash.register.service.GoodsSizeService;
import cn.cash.register.util.LogUtil;

/**
 * 商品尺寸服务接口实现类
 * <p>实现商品尺寸的增、删、改、查</p>
 * @author HuHui
 * @version $Id: GoodsSizeServiceImpl.java, v 0.1 2018年4月17日 下午5:03:40 HuHui Exp $
 */
@Service
public class GoodsSizeServiceImpl implements GoodsSizeService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsSizeServiceImpl.class);

    @Resource
    private GoodsSizeMapper     sizeMapper;

    @Override
    public Long addSize(String sizeName) {
        LogUtil.info(logger, "收到增加商品尺寸请求");
        GoodsSize size = new GoodsSize();
        size.setSizeName(sizeName);
        size.setGmtCreate(new Date());

        return sizeMapper.insertSelective(size);
    }

    @Override
    public List<GoodsSize> queryAll() {
        LogUtil.info(logger, "收到查询所有商品尺寸请求");

        List<GoodsSize> sizes = new ArrayList<>();
        sizes.addAll(sizeMapper.selectAll());

        LogUtil.info(logger, "商品尺寸查询结果,数量:{0}", sizes.size());

        return sizes;
    }

    @Override
    public int update(GoodsSize size) {
        if (size == null) {
            return 0;
        }

        LogUtil.info(logger, "收到商品尺寸修改请求,sizeId={0}", size.getId());

        return sizeMapper.updateByPrimaryKeySelective(size);
    }

    @Override
    public int delete(Long sizeId) {
        LogUtil.info(logger, "收到商品尺寸删除请求,sizeId={0}", sizeId);

        return sizeMapper.deleteByPrimaryKey(sizeId);
    }

}
