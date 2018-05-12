/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import cn.cash.register.util.Money;

/**
 * 商品销售统计
 * @author HuHui
 * @version $Id: GoodsSaleStatistics.java, v 0.1 2018年5月11日 下午5:13:14 HuHui Exp $
 */
public class GoodsSaleStatistics extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 9136473325317154669L;

    private String            goodsName;

    private String            barCode;

    private String            goodsColor;

    private String            goodsSize;

    private String            categoryName;

    private int               goodsStock;

    private int               salesCount;

    private Money             totalAmount;

    private Money             totalActualAmount;

    private Money             totalProfit;

    private double            profitRate;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }

    public int getSalesCount() {
        return salesCount;
    }

    public void setSalesCount(int salesCount) {
        this.salesCount = salesCount;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Money getTotalActualAmount() {
        return totalActualAmount;
    }

    public void setTotalActualAmount(Money totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

    public Money getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Money totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(double profitRate) {
        this.profitRate = profitRate;
    }

}
