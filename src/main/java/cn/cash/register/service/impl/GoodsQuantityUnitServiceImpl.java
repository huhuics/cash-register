/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cash.register.dao.GoodsQuantityUnitMapper;
import cn.cash.register.dao.domain.GoodsQuantityUnit;
import cn.cash.register.service.GoodsQuantityUnitService;

/**
 * 商品单位服务接口实现类
 * @author HuHui
 * @version $Id: GoodsQuantityUnitServiceImpl.java, v 0.1 2018年4月24日 上午9:26:13 HuHui Exp $
 */
@Service
public class GoodsQuantityUnitServiceImpl implements GoodsQuantityUnitService {

    @Resource
    private GoodsQuantityUnitMapper unitMapper;

    @Override
    public Long add(String unitName) {
        GoodsQuantityUnit unit = new GoodsQuantityUnit();
        unit.setUnitName(unitName);
        return unitMapper.insertSelective(unit);
    }

    @Override
    public int delete(Long id) {
        return unitMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(GoodsQuantityUnit unit) {
        return unitMapper.updateByPrimaryKeySelective(unit);
    }

    @Override
    public GoodsQuantityUnit queryById(Long id) {
        return unitMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsQuantityUnit> queryAll() {
        return unitMapper.selectAll();
    }

}
