/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.cash.register.common.request.PromotionAddRequest;
import cn.cash.register.common.request.PromotionQueryRequest;
import cn.cash.register.dao.GoodsInfoMapper;
import cn.cash.register.dao.PromotionDetailMapper;
import cn.cash.register.dao.PromotionGoodsDetailMapper;
import cn.cash.register.dao.domain.PromotionDetail;
import cn.cash.register.dao.domain.PromotionGoodsDetail;
import cn.cash.register.service.PromotionService;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.DateUtil;
import cn.cash.register.util.LogUtil;

/**
 * 促销服务接口实现类
 * @author HuHui
 * @version $Id: PromotionServiceImpl.java, v 0.1 2018年5月16日 下午2:46:12 HuHui Exp $
 */
@Service
public class PromotionServiceImpl implements PromotionService {

    private static final Logger        logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

    @Resource
    private PromotionDetailMapper      promotionMapper;

    @Resource
    private PromotionGoodsDetailMapper promotionGoodsMapper;

    @Resource
    private GoodsInfoMapper            goodsInfoMapper;

    @Resource
    private TransactionTemplate        txTemplate;

    @Override
    public Long add(PromotionAddRequest request) {
        AssertUtil.assertNotNull(request, "参数不能为空");
        request.validate();
        LogUtil.info(logger, "request:{0}", request);
        PromotionDetail item = convert(request);
        LogUtil.info(logger, "Detail:{0}", item);
        return promotionMapper.insertSelective(item);
    }

    @Override
    public int delete(Long id) {
        AssertUtil.assertNotNull(id, "id不能为空");
        return txTemplate.execute(new TransactionCallback<Integer>() {
            @Override
            public Integer doInTransaction(TransactionStatus status) {
                int ret = promotionMapper.deleteByPrimaryKey(id);
                goodsInfoMapper.clearPromotion(id);
                promotionGoodsMapper.deleteByPromotionId(id);
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
        PromotionDetail promotionDetail = promotionMapper.selectByPrimaryKey(id);
        if (isExpired(promotionDetail.getStartTime(), promotionDetail.getEndTime())) {
            promotionDetail.setStatus(false);
            promotionMapper.updateByPrimaryKeySelective(promotionDetail);
        }
        return promotionDetail;
    }

    @Override
    public PromotionGoodsDetail getPromotion(Long goodsId, Long promotionId) {

        if (goodsId == null || promotionId == null) {
            return null;
        }

        PromotionDetail promotionDetail = promotionMapper.selectByPrimaryKey(promotionId);

        if (promotionDetail == null || isExpired(promotionDetail.getStartTime(), promotionDetail.getEndTime())) {
            return null;
        }

        PromotionGoodsDetail goodsPromotionDetail = promotionGoodsMapper.queryGoodsPromotionDetail(goodsId, promotionId);

        return goodsPromotionDetail;
    }

    @Override
    public PageInfo<PromotionDetail> list(PromotionQueryRequest request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageHelper.orderBy(request.getSidx() + " " + request.getOrder());
        List<PromotionDetail> details = promotionMapper.list(request);

        //如果过了促销日期则修改状态
        for (PromotionDetail detail : details) {
            if (isExpired(detail.getStartTime(), detail.getEndTime())) {
                detail.setStatus(false);
                promotionMapper.updateByPrimaryKeySelective(detail);
            }
        }

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

    private PromotionDetail convert(PromotionAddRequest request) {
        PromotionDetail detail = new PromotionDetail();
        detail.setPromotionName(request.getPromotionName());
        detail.setPromotionType(request.getPromotionType());
        detail.setIsMemberOnly(request.getIsMemberOnly());
        detail.setIsMemberDiscountTwice(request.getIsMemberDiscountTwice());
        try {
            if (StringUtils.isNotBlank(request.getStartTime())) {
                detail.setStartTime(DateUtil.parseDateWebFormat(request.getStartTime()));
            }
            if (StringUtils.isNotBlank(request.getEndTime())) {
                detail.setEndTime(DateUtil.parseDateWebFormat(request.getEndTime()));
            }
        } catch (Exception e) {
            throw new RuntimeException("日期格式错误", e);
        }
        detail.setStatus(true);
        detail.setGmtCreate(new Date());
        return detail;
    }

}
