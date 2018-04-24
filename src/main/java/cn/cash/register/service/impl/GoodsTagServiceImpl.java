/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.cash.register.dao.GoodsTagMapper;
import cn.cash.register.dao.domain.GoodsTag;
import cn.cash.register.service.GoodsTagService;

/**
 * 商品标签服务接口实现类
 * @author HuHui
 * @version $Id: GoodsTagServiceImpl.java, v 0.1 2018年4月24日 下午3:24:13 HuHui Exp $
 */
@Service
public class GoodsTagServiceImpl implements GoodsTagService {

    @Resource
    private GoodsTagMapper tagMapper;

    @Override
    public Long add(String tagName) {
        GoodsTag tag = new GoodsTag();
        tag.setTagName(tagName);
        tag.setGmtCreate(new Date());
        return tagMapper.insertSelective(tag);
    }

    @Override
    public int delete(Long id) {
        return tagMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(GoodsTag tag) {
        return tagMapper.updateByPrimaryKeySelective(tag);
    }

    @Override
    public GoodsTag queryById(Long id) {
        return tagMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<GoodsTag> queryAll() {
        return tagMapper.selectAll();
    }

}
