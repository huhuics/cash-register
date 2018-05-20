/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 打折与特价商品
 * @author HuHui
 * @version $Id: DiscountGoodsDetail.java, v 0.1 2018年5月17日 上午11:26:37 HuHui Exp $
 */
public class DiscountGoodsDetail extends BaseDomain {

    /**  */
    private static final long serialVersionUID = -4007517191850097112L;

    private Double            discount;

    private String            price;

    public DiscountGoodsDetail() {
    }

    public DiscountGoodsDetail(Double discount, String price) {
        this.discount = discount;
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
