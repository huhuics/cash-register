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
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.cash.register.dao.GoodsCategoryMapper;
import cn.cash.register.dao.domain.GoodsCategory;
import cn.cash.register.service.GoodsCategoryService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.LogUtil;

/**
 * 商品种类服务接口实现类
 * @author HuHui
 * @version $Id: GoodsCategoryServiceImpl.java, v 0.1 2018年4月17日 下午7:56:53 HuHui Exp $
 */
@Service
public class GoodsCategoryServiceImpl implements GoodsCategoryService {

    private static final Logger logger  = LoggerFactory.getLogger(GoodsCategoryServiceImpl.class);

    private static final long   ROOT_ID = 1L;

    @Resource
    private GoodsCategoryMapper goodsCategoryMapper;

    @Resource
    private TransactionTemplate txTemplate;

    @Override
    public long add(GoodsCategory category) {

        AssertUtil.assertNotNull(category, "参数不能为空");

        LogUtil.info(logger, "收到增加商品种类请求");

        long id = goodsCategoryMapper.insertWithKey(category);

        return id;
    }

    @Override
    public void delete(Long categoryId) {
        if (categoryId == null) {
            return;
        }

        LogUtil.info(logger, "收到商品种类删除请求,categoryId={0}", categoryId);

        AssertUtil.assertNotEquals(categoryId, ROOT_ID, "root节点不能删除");

        txTemplate.execute(new TransactionCallbackWithoutResult() {

            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                //1.删除结点
                goodsCategoryMapper.deleteByPrimaryKey(categoryId);

                //2.查询当前节点的所有子节点
                List<GoodsCategory> children = goodsCategoryMapper.selectByParentId(categoryId);

                //3.遍历子节点并删除
                for (GoodsCategory child : children) {
                    delete(child.getId());
                }

            }
        });

    }

    @Override
    public int update(GoodsCategory category) {
        if (category == null) {
            return 0;
        }

        LogUtil.info(logger, "收到商品种类修改请求,categoryId={0}", category.getId());

        return goodsCategoryMapper.updateByPrimaryKeySelective(category);
    }

    @Override
    public JSONArray getTree(Long categoryId) {

        AssertUtil.assertTrue(categoryId > 0L, "id必须大约0");

        //1.查询当前节点的所有子节点
        List<GoodsCategory> children = goodsCategoryMapper.selectByParentId(categoryId);

        JSONArray childTree = new JSONArray();

        //2.遍历子节点
        for (GoodsCategory child : children) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("id", child.getId());
            jsonObj.put("categoryName", child.getCategoryName());
            jsonObj.put("parentId", child.getParentId());

            //递归调用
            JSONArray tree = getTree(child.getId());
            if (!tree.isEmpty()) {
                jsonObj.put("children", tree);
            }

            childTree.fluentAdd(jsonObj);
        }

        return childTree;
    }

}
