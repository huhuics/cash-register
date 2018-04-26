/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 商品销售明细查询请求
 * @author HuHui
 * @version $Id: TradeGoodsDetailQueryRequest.java, v 0.1 2018年4月21日 下午3:52:32 HuHui Exp $
 */
public class TradeGoodsDetailQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = 8399244355078476330L;

    private String            categoryName;

    private String            goodsBrand;

    private String            supplierName;

    private String            goodsTag;

    private String            tradeTimeUp;

    private String            tradeTimeDown;

    private String            barCode;

    private String            goodsName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getTradeTimeUp() {
        return tradeTimeUp;
    }

    public void setTradeTimeUp(String tradeTimeUp) {
        this.tradeTimeUp = tradeTimeUp;
    }

    public String getTradeTimeDown() {
        return tradeTimeDown;
    }

    public void setTradeTimeDown(String tradeTimeDown) {
        this.tradeTimeDown = tradeTimeDown;
    }

}
