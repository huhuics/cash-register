/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import java.util.List;

/**
 * 购物小票
 * @author HuHui
 * @version $Id: ShoppingReceipt.java, v 0.1 2018年5月25日 下午3:24:51 HuHui Exp $
 */
public class ShoppingReceipt extends BaseDomain {

    /**  */
    private static final long      serialVersionUID = -2580794795916480271L;

    private String                 tradeNo;

    private String                 tradeTime;

    private String                 memberNo;

    private String                 goodsCount;

    private String                 totalAmount;

    private String                 goodsDiscount;

    private String                 totalActualAmount;

    private String                 sellerNo;

    private String                 change;                                  //找零

    private List<TradeGoodsDetail> goodsInfo;

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(String tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(String goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public String getTotalActualAmount() {
        return totalActualAmount;
    }

    public void setTotalActualAmount(String totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public List<TradeGoodsDetail> getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(List<TradeGoodsDetail> goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

}
