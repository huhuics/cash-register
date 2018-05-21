/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.PromotionDetailMapper;
import cn.cash.register.dao.domain.DiscountGoodsDetail;
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
    public Long add(PromotionDetail item, List<Long> goodsIds, List<DiscountGoodsDetail> discountGoodsList) {
        checkAndSet(item, goodsIds, discountGoodsList);

        Map<Long, DiscountGoodsDetail> disCountGoodsDetailMap = new HashMap<Long, DiscountGoodsDetail>();
        for (DiscountGoodsDetail detail : discountGoodsList) {
            disCountGoodsDetailMap.put(detail.getGoodsId(), detail);
        }

        return txTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                item.setDetail(JSON.toJSONString(disCountGoodsDetailMap));
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
    @SuppressWarnings("unchecked")
    public DiscountGoodsDetail getPromotion(Long goodsId, Long promotionId) {

        if (goodsId == null || promotionId == null) {
            return null;
        }

        PromotionDetail promotionDetail = promotionMapper.selectByPrimaryKey(promotionId);

        if (promotionDetail == null || isExpired(promotionDetail.getStartTime(), promotionDetail.getEndTime())) {
            return null;
        }

        if (StringUtils.isBlank(promotionDetail.getDetail())) {
            return null;
        }

        String detail = promotionDetail.getDetail();

        Map<Long, DiscountGoodsDetail> parseMap = JSON.parseObject(detail, Map.class);

        DiscountGoodsDetail discountGoodsDetail = parseMap.get(goodsId);

        return discountGoodsDetail;
    }

    @Override
    public PageInfo<PromotionDetail> list(PromotionQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<PromotionDetail> details = promotionMapper.list(request);
        return new PageInfo<PromotionDetail>(details);
    }

    /**
     * 判断促销是否过期
     * 过期返回true，没过期返回false
     */
    private boolean isExpired(Date startTime, Date endTime) {
        Date now = new Date();
        if (startTime == null && endTime != null) {
            if (now.before(endTime)) {
                return false;
            }
            return true;
        } else if (startTime != null && endTime == null) {
            if (now.after(startTime)) {
                return false;
            }
            return true;
        } else if (startTime != null && endTime != null) { //都不为空
            if (now.after(startTime) && now.before(endTime)) {
                return false;
            }
            return true;
        }

        return false;
    }

    /**
     * 参数校验并赋值
     */
    private void checkAndSet(PromotionDetail item, List<Long> goodsIds, List<DiscountGoodsDetail> discountGoodsList) {
        AssertUtil.assertNotNull(item, "参数不能为空");
        AssertUtil.assertNotBlank(item.getPromotionName(), "促销名称不能为空");
        AssertUtil.assertNotBlank(item.getPromotionType(), "促销类型不能为空");
        AssertUtil.assertNotBlank(goodsIds, "促销商品不能为空");
        AssertUtil.assertNotBlank(discountGoodsList, "促销商品不能为空");
        item.setGmtCreate(new Date());
        item.setStatus(true);
    }

}
