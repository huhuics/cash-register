package cn.cash.register.dao.domain;

import cn.cash.register.util.Money;
import java.util.Date;

public class GoodsDetail {
    private Long id;

    private Long goodsImageId;

    private String goodsName;

    private String barCode;

    private String productNumber;

    private String pinyinCode;

    private String categoryName;

    private Boolean goodsStatus;

    private String goodsBrand;

    private String goodsColor;

    private String goodsSize;

    private Integer goodsStock;

    private String quantityUnit;

    private Integer stockUpperLimit;

    private Integer stockLowerLimit;

    private Money importPrice;

    private Money averageImportPrice;

    private Money salesPrice;

    private Money tradePrice;

    private Money vipPrice;

    private Boolean isVipDiscount;

    private String supplierName;

    private Date productionDate;

    private Integer qualityGuaranteePeriod;

    private Boolean isIntegral;

    private Boolean royaltyType;

    private Boolean isBooked;

    private Boolean isGift;

    private Boolean isWeigh;

    private Boolean isFixedPrice;

    private Boolean isTimeingPrice;

    private Boolean isHidden;

    private String remark;

    private Date gmtUpdate;

    private Date gmtCreate;

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
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber == null ? null : productNumber.trim();
    }

    public String getPinyinCode() {
        return pinyinCode;
    }

    public void setPinyinCode(String pinyinCode) {
        this.pinyinCode = pinyinCode == null ? null : pinyinCode.trim();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
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
        this.goodsBrand = goodsBrand == null ? null : goodsBrand.trim();
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor == null ? null : goodsColor.trim();
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize == null ? null : goodsSize.trim();
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
        this.quantityUnit = quantityUnit == null ? null : quantityUnit.trim();
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

    public Money getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(Money importPrice) {
        this.importPrice = importPrice;
    }

    public Money getAverageImportPrice() {
        return averageImportPrice;
    }

    public void setAverageImportPrice(Money averageImportPrice) {
        this.averageImportPrice = averageImportPrice;
    }

    public Money getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(Money salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Money getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(Money tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Money getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Money vipPrice) {
        this.vipPrice = vipPrice;
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
        this.supplierName = supplierName == null ? null : supplierName.trim();
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
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

    public Boolean getRoyaltyType() {
        return royaltyType;
    }

    public void setRoyaltyType(Boolean royaltyType) {
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
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}