/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.cash.register.dao.GoodsCategoryMapper;
import cn.cash.register.dao.domain.GoodsCategory;
import cn.cash.register.dao.domain.GoodsCategoryNode;
import cn.cash.register.service.GoodsCategoryService;
import cn.cash.register.util.LogUtil;

/**
 * 商品种类服务接口实现类
 * @author HuHui
 * @version $Id: GoodsCategoryServiceImpl.java, v 0.1 2018年4月17日 下午7:56:53 HuHui Exp $
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    @Resource
    private GoodsCategoryMapper categoryMapper;

    @Override
    public long add(GoodsCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("参数为空");
        }

        LogUtil.info(logger, "收到增加商品种类请求");

        long id = categoryMapper.insertWithKey(category);

        return id;
    }

    @Override
    public void delete(Long categoryId) {
        if (categoryId == null) {
            return;
        }
        LogUtil.info(logger, "收到商品种类删除请求,categoryId={0}", categoryId);

        //删除结点
        categoryMapper.deleteByPrimaryKey(categoryId);

        //删除子孙结点
        categoryMapper.deleteChildren(categoryId);
    }

    @Override
    public int update(GoodsCategory category) {
        if (category == null) {
            return 0;
        }

        LogUtil.info(logger, "收到商品种类修改请求,categoryId={0}", category.getId());

        return categoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public Set<GoodsCategoryNode> queryAll() {
        LogUtil.info(logger, "收到查询所有商品种类请求");
        Set<GoodsCategoryNode> nodes = new LinkedHashSet<>();

        //1.查询所有

        return nodes;
    }

}
