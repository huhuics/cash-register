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

    private Integer           goodsDiscount;

    private String            barCode;                                 //未使用，只为方便前台传值
    private String            salesPrice;                              //未使用，只为方便前台传值
    private String            isVipDiscount;                           //未使用，只为方便前台传值
    private String            vipPrice;                                //未使用，只为方便前台传值

    /***********收银时以下字段为正;退款/反结账时以下字段为负************/

    private String            totalAmount;

    private Integer           goodsCount;

    private String            totalActualAmount;

    /***************************end****************************/

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Integer goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
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

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getIsVipDiscount() {
        return isVipDiscount;
    }

    public void setIsVipDiscount(String isVipDiscount) {
        this.isVipDiscount = isVipDiscount;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

}
