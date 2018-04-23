/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.util.AssertUtil;
import cn.cash.register.util.Money;

/**
 * 反结账请求类
 * @author HuHui
 * @version $Id: CancelRequest.java, v 0.1 2018年4月23日 下午7:35:46 HuHui Exp $
 */
public class CancelRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -5069321570062921043L;

    private String            tradeNo;

    private long              memberId;

    private List<GoodsItem>   goodsItems;

    private Long              totalActualAmount;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(tradeNo, "交易编号不能为空");
        AssertUtil.assertNotNull(totalActualAmount, "实收总金额不能为空");
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(goodsItems), "收银商品不能为空");
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public List<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public Long getTotalActualAmount() {
        return totalActualAmount;
    }

    public Money getTotalActualAmountMoney() {
        return new Money(totalActualAmount);
    }

    public void setTotalActualAmount(Long totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

}
