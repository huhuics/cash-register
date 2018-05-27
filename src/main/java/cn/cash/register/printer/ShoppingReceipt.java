/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.util.List;

import cn.cash.register.dao.domain.BaseDomain;
import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.dao.domain.PayChenal;

/**
 * 购物小票
 * @author HuHui
 * @version $Id: ShoppingReceipt.java, v 0.1 2018年5月25日 下午3:24:51 HuHui Exp $
 */
public class ShoppingReceipt extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -2580794795916480271L;

    private String            shopName;

    private String            tradeNo;

    private String            tradeTime;

    private String            memberNo;

    private String            goodsCount;

    private String            totalAmount;

    private String            goodsDiscount;

    private String            totalActualAmount;

    private String            sellerNo;

    private String            change;                                  //找零

    private List<GoodsItem>   goodsItems;

    private List<PayChenal>   payChenals;

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

    public List<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public List<PayChenal> getPayChenals() {
        return payChenals;
    }

    public void setPayChenals(List<PayChenal> payChenals) {
        this.payChenals = payChenals;
    }

}
