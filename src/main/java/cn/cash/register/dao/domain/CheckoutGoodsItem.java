/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 收银时从前台传到后台的待收银商品对象
 * @author HuHui
 * @version $Id: CheckoutGoodsItem.java, v 0.1 2018年4月23日 上午11:08:50 HuHui Exp $
 */
public class CheckoutGoodsItem extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -3156888388686240382L;

    private long              goodsId;

    private long              totalAmount;

    private int               goodsDiscount;

    private int               goodsCount;

    private long              totalActualAmount;

    private long              profitAmount;

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(int goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public int getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(int goodsCount) {
        this.goodsCount = goodsCount;
    }

    public long getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(long profitAmount) {
        this.profitAmount = profitAmount;
    }

    public long getTotalActualAmount() {
        return totalActualAmount;
    }

    public void setTotalActualAmount(long totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

}
