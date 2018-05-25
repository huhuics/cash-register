/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.alibaba.fastjson.JSON;

import cn.cash.register.common.request.GoodsStockCheckQueryRequest;
import cn.cash.register.dao.GoodsStockCheckDetailMapper;
import cn.cash.register.dao.GoodsStockCheckMapper;
import cn.cash.register.dao.domain.GoodsStockCheck;
import cn.cash.register.dao.domain.GoodsStockCheckDetail;
import cn.cash.register.service.GoodsStockCheckServie;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.Money;

/**
 * 盘点服务接口实现类
 * @author HuHui
 * @version $Id: GoodsStockCheckServieImpl.java, v 0.1 2018年5月9日 下午8:51:17 HuHui Exp $
 */
@Service
public class GoodsStockCheckServieImpl implements GoodsStockCheckServie {

    @Resource
    private GoodsStockCheckMapper       checkMapper;

    @Resource
    private GoodsStockCheckDetailMapper checkDetailMapper;

    @Resource
    private TransactionTemplate         txTemplate;

    @Override
    public Long addCheck(String sellerNo, String remark, String detailsStr) {
        AssertUtil.assertNotBlank(sellerNo, "sellerNo参数不能为空");
        AssertUtil.assertNotBlank(detailsStr, "参数不能为空");

        GoodsStockCheck check = new GoodsStockCheck();
        check.setCheckDate(new Date());
        check.setSellerNo(sellerNo);
        check.setRemark(remark);
        check.setGmtCreate(new Date());

        List<GoodsStockCheckDetail> details = JSON.parseArray(detailsStr, GoodsStockCheckDetail.class);

        return txTemplate.execute(new TransactionCallback<Long>() {
            @Override
            public Long doInTransaction(TransactionStatus status) {
                Long checkId = checkMapper.insertSelective(check);
                addCheckDetail(checkId, details);
                return checkId;
            }
        });
    }

    @Override
    public List<GoodsStockCheck> queryCheck(GoodsStockCheckQueryRequest request) {
        AssertUtil.assertNotNull(request, "参数不能为空");
        request.validate();
        return checkMapper.query(request);
    }

    private void addCheckDetail(Long checkId, List<GoodsStockCheckDetail> details) {
        for (GoodsStockCheckDetail detail : details) {
            if (NumberUtils.isDigits(detail.getProfitLossAmountStr())) {
                detail.setProfitLossAmount(new Money(detail.getProfitLossAmountStr()));
            }
            detail.setCheckId(checkId);
            detail.setGmtCreate(new Date());
            checkDetailMapper.insert(detail);
        }
    }

    @Override
    public List<GoodsStockCheckDetail> queryCheckDetail(Long stockCheckId) {
        AssertUtil.assertNotNull(stockCheckId, "参数不能为空");
        return checkDetailMapper.queryByCheckId(stockCheckId);
    }

}
