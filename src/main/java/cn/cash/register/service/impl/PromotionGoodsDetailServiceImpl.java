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
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.PromotionGoodsDetailMapper;
import cn.cash.register.dao.domain.GoodsInfo;
import cn.cash.register.dao.domain.PromotionGoodsDetail;
import cn.cash.register.service.PromotionGoodsDetailService;
import cn.cash.register.util.AssertUtil;

/**
 * 促销商品详情接口实现类
 * @author HuHui
 * @version $Id: PromotionGoodsDetailServiceImpl.java, v 0.1 2018年5月21日 下午9:30:37 HuHui Exp $
 */
@Service
public class PromotionGoodsDetailServiceImpl implements PromotionGoodsDetailService {

    @Resource
    private PromotionGoodsDetailMapper promotionGoodsDetailMapper;

    @Resource
    private TransactionTemplate        txTemplate;

    @Resource
    private GoodsInfoMapper            goodsInfoMapper;

    @Override
    public void addOrUpdate(List<PromotionGoodsDetail> details) {
        AssertUtil.assertNotBlank(details, "促销商品不能为空");
        txTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                for (PromotionGoodsDetail detail : details) {
                    if (detail.getId() == null) {
                        detail.setGmtCreate(new Date());
                        promotionGoodsDetailMapper.insertSelective(detail);

                        GoodsInfo goodsInfo = goodsInfoMapper.selectByPrimaryKey(detail.getGoodsId());
                        goodsInfo.setPromotionId(detail.getPromotionId());
                        goodsInfoMapper.updateByPrimaryKeySelective(goodsInfo);
                    } else {
                        promotionGoodsDetailMapper.updateByPrimaryKey(detail);
                    }
                }
            }
        });

    }

    @Override
    public int delete(Long id) {
        AssertUtil.assertNotNull(id, "促销商品id不能为空");
        return promotionGoodsDetailMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PromotionGoodsDetail> queryByPromotionId(Long promotionId) {
        AssertUtil.assertNotNull(promotionId, "促销id不能为空");
        List<PromotionGoodsDetail> promotionGoods = promotionGoodsDetailMapper.selectByPromotionId(promotionId);
        return promotionGoods;
    }

}
