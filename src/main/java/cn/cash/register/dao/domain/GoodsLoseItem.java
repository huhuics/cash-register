/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 报损的商品详情
 * @author HuHui
 * @version $Id: GoodsLoseItem.java, v 0.1 2018年4月26日 下午6:55:36 HuHui Exp $
 */
public class GoodsLoseItem extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 2441333714964803349L;

    private String            goodsName;

    private String            barCode;

    private String            goodsColor;

    private String            goodsSize;

    private Integer           loseCount;

    private Double            loseAmount;

    private String            loseReason;

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

    public Integer getLoseCount() {
        return loseCount;
    }

    public void setLoseCount(Integer loseCount) {
        this.loseCount = loseCount;
    }

    public Double getLoseAmount() {
        return loseAmount;
    }

    public void setLoseAmount(Double loseAmount) {
        this.loseAmount = loseAmount;
    }

    public String getLoseReason() {
        return loseReason;
    }

    public void setLoseReason(String loseReason) {
        this.loseReason = loseReason;
    }

}
