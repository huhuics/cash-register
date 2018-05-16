/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.PromotionDetailMapper;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.PromotionDetail;
import cn.cash.register.service.PromotionService;
import cn.cash.register.util.AssertUtil;

/**
 * 促销服务接口实现类
 * @author HuHui
 * @version $Id: PromotionServiceImpl.java, v 0.1 2018年5月16日 下午2:46:12 HuHui Exp $
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    @Resource
    private PromotionDetailMapper promotionMapper;

    @Resource
    private GoodsInfoMapper       goodsInfoMapper;

    @Resource
    private TransactionTemplate   txTemplate;

    @Override
    public Long add(PromotionDetail item, List<Long> goodsIds) {
        checkAndSet(item, goodsIds);

        return txTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                Long promotionId = promotionMapper.insertSelective(item);
                for (Long goodsId : goodsIds) {
                    GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(goodsId);
                    goodsInfo.setPromotionId(promotionId);
                    goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
                }
                return promotionId;
            }
        });
    }

    @Override
    public int delete(Long id) {
        AssertUtil.assertNotNull(id, "id不能为空");
        return txTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                int ret = promotionMapper.deleteByPrimaryKey(id);
                goodsInfoMapper.clearPromotion(id);
                return ret;
            }
        });
    }

    @Override
    public int update(PromotionDetail item) {
        return promotionMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public PromotionDetail queryById(Long id) {
        return promotionMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<PromotionDetail> list(PromotionQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<PromotionDetail> details = promotionMapper.list(request);
        return new PageInfo<PromotionDetail>(details);
    }

    /**
     * 参数校验并赋值
     */
    private void checkAndSet(PromotionDetail item, List<Long> goodsIds) {
        AssertUtil.assertNotNull(item, "参数不能为空");
        AssertUtil.assertNotBlank(item.getPromotionName(), "促销名称不能为空");
        AssertUtil.assertNotBlank(item.getPromotionType(), "促销类型不能为空");
        AssertUtil.assertNotBlank(goodsIds, "促销商品不能为空");
        item.setGmtCreate(new Date());
        item.setStatus(true);
    }

}
