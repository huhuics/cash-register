/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 收银时从前台传到后台的待收银商品对象
 * @author HuHui
 * @version $Id: GoodsItem.java, v 0.1 2018年4月23日 上午11:08:50 HuHui Exp $
 */
public class GoodsItem extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -3156888388686240382L;

    private Long              goodsId;

    private String            goodsName;

    private int               goodsDiscount;

    /***********收银时以下字段为正;退款/反结账时以下字段为负************/

    private String            totalAmount;

    private int               goodsCount;

    private String            totalActualAmount;

    /***************************end****************************/

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalActualAmount() {
        return totalActualAmount;
    }

    public void setTotalActualAmount(String totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

}
