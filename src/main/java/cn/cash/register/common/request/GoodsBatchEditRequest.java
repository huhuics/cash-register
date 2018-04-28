/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 批量编辑商品请求
 * 
 * @author 51
 * @version $Id: GoodsBatchEditRequest.java, v 0.1 2018年4月28日 上午10:25:46 51 Exp $
 */
public class GoodsBatchEditRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -323432957045103431L;

    /**
     * 批量编辑商品id(半角逗号分隔) 
     */
    private String            targetIds;

    /**
     * 提成方式，0~5 
     */
    private String            royaltyType;

    /**
     * 提成方式对应值 
     */
    private String            royaltyValue;

    /**
     * 商品分类 
     */
    private String            categoryName;

    /**
     * 添加标签(半角逗号分隔) 
     */
    private String            goodsTagAdd;

    /**
     * 移除标签(半角逗号分隔) 
     */
    private String            goodsTagRemove;

    /**
     * 商品品牌 
     */
    private String            goodsBrand;

    /**
     * 供货商 
     */
    private String            supplierName;

    /**
     * 是否积分 
     */
    private String            isIntegral;

    /**
     * 会员优惠情况 
     */
    private String            isVipPrice;

    /**
     * 会员折扣 
     */
    private String            vipPricePercent;

    /**
     * 商品状态 
     */
    private String            goodsStatus;

    /**
     * 是否赠品 
     */
    private String            isGift;

    /**
     * 是否隐藏 
     */
    private String            isHidden;

    /**
     * 能否预约 
     */
    private String            isBooked;

    /**
     * 是否称重 
     */
    private String            isWeigh;

    /**
     * 是否时价 
     */
    private String            isTimeingPrice;

    /**
     * 是否固价 
     */
    private String            isFixedPrice;

    @Override
    public void validate() {
        // TODO Auto-generated method stub

    }

    public String getTargetIds() {
        return targetIds;
    }

    public void setTargetIds(String targetIds) {
        this.targetIds = targetIds;
    }

    public String getRoyaltyType() {
        return royaltyType;
    }

    public void setRoyaltyType(String royaltyType) {
        this.royaltyType = royaltyType;
    }

    public String getRoyaltyValue() {
        return royaltyValue;
    }

    public void setRoyaltyValue(String royaltyValue) {
        this.royaltyValue = royaltyValue;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getGoodsTagAdd() {
        return goodsTagAdd;
    }

    public void setGoodsTagAdd(String goodsTagAdd) {
        this.goodsTagAdd = goodsTagAdd;
    }

    public String getGoodsTagRemove() {
        return goodsTagRemove;
    }

    public void setGoodsTagRemove(String goodsTagRemove) {
        this.goodsTagRemove = goodsTagRemove;
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

    public String getIsIntegral() {
        return isIntegral;
    }

    public void setIsIntegral(String isIntegral) {
        this.isIntegral = isIntegral;
    }

    public String getIsVipPrice() {
        return isVipPrice;
    }

    public void setIsVipPrice(String isVipPrice) {
        this.isVipPrice = isVipPrice;
    }

    public String getVipPricePercent() {
        return vipPricePercent;
    }

    public void setVipPricePercent(String vipPricePercent) {
        this.vipPricePercent = vipPricePercent;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getIsGift() {
        return isGift;
    }

    public void setIsGift(String isGift) {
        this.isGift = isGift;
    }

    public String getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(String isHidden) {
        this.isHidden = isHidden;
    }

    public String getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(String isBooked) {
        this.isBooked = isBooked;
    }

    public String getIsWeigh() {
        return isWeigh;
    }

    public void setIsWeigh(String isWeigh) {
        this.isWeigh = isWeigh;
    }

    public String getIsTimeingPrice() {
        return isTimeingPrice;
    }

    public void setIsTimeingPrice(String isTimeingPrice) {
        this.isTimeingPrice = isTimeingPrice;
    }

    public String getIsFixedPrice() {
        return isFixedPrice;
    }

    public void setIsFixedPrice(String isFixedPrice) {
        this.isFixedPrice = isFixedPrice;
    }

}
