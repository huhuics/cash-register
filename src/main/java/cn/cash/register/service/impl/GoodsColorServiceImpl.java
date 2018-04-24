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

import cn.cash.register.dao.GoodsColorMapper;
import cn.cash.register.dao.domain.GoodsColor;
import cn.cash.register.service.GoodsColorService;
import cn.cash.register.util.LogUtil;

/**
 * 商品颜色服务接口实现类
 * <p>实现商品颜色的增、删、改、查</p>
 * @author HuHui
 * @version $Id: GoodsColorServiceImpl.java, v 0.1 2018年4月17日 下午4:50:14 HuHui Exp $
 */
@Service
public class GoodsColorServiceImpl implements GoodsColorService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsColorServiceImpl.class);

    @Resource
    private GoodsColorMapper    colorMapper;

    @Override
    public Long addColor(String colorName) {
        LogUtil.info(logger, "收到增加商品颜色请求");
        GoodsColor color = new GoodsColor();
        color.setColor(colorName);
        color.setGmtCreate(new Date());

        return colorMapper.insertSelective(color);
    }

    @Override
    public List<GoodsColor> queryAll() {
        LogUtil.info(logger, "收到查询所有商品颜色请求");

        List<GoodsColor> colors = new ArrayList<>();
        colors.addAll(colorMapper.selectAll());

        LogUtil.info(logger, "商品颜色查询结果,数量:{0}", colors.size());

        return colors;
    }

    @Override
    public int update(GoodsColor color) {
        if (color == null) {
            return 0;
        }

        LogUtil.info(logger, "收到商品颜色修改请求,colorId={0}", color.getId());

        return colorMapper.updateByPrimaryKeySelective(color);
    }

    @Override
    public int delete(Long colorId) {
        LogUtil.info(logger, "收到商品颜色删除请求,colorId={0}", colorId);
        return colorMapper.deleteByPrimaryKey(colorId);
    }

}
