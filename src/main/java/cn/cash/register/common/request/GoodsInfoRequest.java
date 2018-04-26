/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 商品信息请求类
 * 增加/修改商品
 * @author HuHui
 * @version $Id: GoodsInfoRequest.java, v 0.1 2018年4月25日 下午8:12:03 HuHui Exp $
 */
public class GoodsInfoRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -4032486780540066578L;

    private Long              id;

    private Long              goodsImageId;

    private String            goodsName;

    private String            barCode;

    private String            productNumber;

    private String            pinyinCode;

    private String            categoryName;

    private Boolean           goodsStatus;

    private String            goodsBrand;

    private String            goodsColor;

    private String            goodsSize;

    private String            goodsTag;

    private Integer           goodsStock;

    private String            quantityUnit;

    private Integer           stockUpperLimit;

    private Integer           stockLowerLimit;

    private String            lastImportPrice;

    private String            averageImportPrice;

    private String            salesPrice;

    private String            tradePrice;

    private String            vipPrice;

    private Boolean           isVipDiscount;

    private String            supplierName;

    private String            productionDate;

    private Integer           qualityGuaranteePeriod;

    private Boolean           isIntegral;

    private String            royaltyType;

    private Boolean           isBooked;

    private Boolean           isGift;

    private Boolean           isWeigh;

    private Boolean           isFixedPrice;

    private Boolean           isTimeingPrice;

    private Boolean           isHidden;

    private String            remark;

    private String            gmtUpdate;

    private String            gmtCreate;

    @Override
    public void validate() {
        // TODO Auto-generated method stub

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsImageId() {
        return goodsImageId;
    }

    public void setGoodsImageId(Long goodsImageId) {
        this.goodsImageId = goodsImageId;
    }

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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Boolean getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(Boolean goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
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

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public Integer getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(Integer goodsStock) {
        this.goodsStock = goodsStock;
    }

    public String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Integer getStockUpperLimit() {
        return stockUpperLimit;
    }

    public void setStockUpperLimit(Integer stockUpperLimit) {
        this.stockUpperLimit = stockUpperLimit;
    }

    public Integer getStockLowerLimit() {
        return stockLowerLimit;
    }

    public void setStockLowerLimit(Integer stockLowerLimit) {
        this.stockLowerLimit = stockLowerLimit;
    }

    public Boolean getIsVipDiscount() {
        return isVipDiscount;
    }

    public void setIsVipDiscount(Boolean isVipDiscount) {
        this.isVipDiscount = isVipDiscount;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public Integer getQualityGuaranteePeriod() {
        return qualityGuaranteePeriod;
    }

    public void setQualityGuaranteePeriod(Integer qualityGuaranteePeriod) {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    public Boolean getIsIntegral() {
        return isIntegral;
    }

    public void setIsIntegral(Boolean isIntegral) {
        this.isIntegral = isIntegral;
    }

    public String getRoyaltyType() {
        return royaltyType;
    }

    public void setRoyaltyType(String royaltyType) {
        this.royaltyType = royaltyType;
    }

    public Boolean getIsBooked() {
        return isBooked;
    }

    public void setIsBooked(Boolean isBooked) {
        this.isBooked = isBooked;
    }

    public Boolean getIsGift() {
        return isGift;
    }

    public void setIsGift(Boolean isGift) {
        this.isGift = isGift;
    }

    public Boolean getIsWeigh() {
        return isWeigh;
    }

    public void setIsWeigh(Boolean isWeigh) {
        this.isWeigh = isWeigh;
    }

    public Boolean getIsFixedPrice() {
        return isFixedPrice;
    }

    public void setIsFixedPrice(Boolean isFixedPrice) {
        this.isFixedPrice = isFixedPrice;
    }

    public Boolean getIsTimeingPrice() {
        return isTimeingPrice;
    }

    public void setIsTimeingPrice(Boolean isTimeingPrice) {
        this.isTimeingPrice = isTimeingPrice;
    }

    public Boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(Boolean isHidden) {
        this.isHidden = isHidden;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLastImportPrice() {
        return lastImportPrice;
    }

    public void setLastImportPrice(String lastImportPrice) {
        this.lastImportPrice = lastImportPrice;
    }

    public String getAverageImportPrice() {
        return averageImportPrice;
    }

    public void setAverageImportPrice(String averageImportPrice) {
        this.averageImportPrice = averageImportPrice;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public String getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(String tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(String vipPrice) {
        this.vipPrice = vipPrice;
    }

    public String getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(String gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
