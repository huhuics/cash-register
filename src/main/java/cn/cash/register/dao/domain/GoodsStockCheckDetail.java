package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class GoodsStockCheckDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -6407848122107524695L;

    private Long              id;

    private Long              checkId;

    private String            goodsName;

    private String            barCode;

    private String            goodsColor;

    private String            goodsSize;

    private String            productNumber;

    private Integer           primaryGoodsStock;

    private Integer           checkedGoodsStock;

    private Integer           stockDiff;

    /**
     * Controller不需填充此参数
     */
    private Money             profitLossAmount;

    /**
     * Controller需填充此参数,单位:元
     * profitLossAmount = stockDiff * average_import_price
     */
    private String            profitLossAmountStr;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
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

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber == null ? null : productNumber.trim();
    }

    public Integer getPrimaryGoodsStock() {
        return primaryGoodsStock;
    }

    public void setPrimaryGoodsStock(Integer primaryGoodsStock) {
        this.primaryGoodsStock = primaryGoodsStock;
    }

    public Integer getCheckedGoodsStock() {
        return checkedGoodsStock;
    }

    public void setCheckedGoodsStock(Integer checkedGoodsStock) {
        this.checkedGoodsStock = checkedGoodsStock;
    }

    public Integer getStockDiff() {
        return stockDiff;
    }

    public void setStockDiff(Integer stockDiff) {
        this.stockDiff = stockDiff;
    }

    public Money getProfitLossAmount() {
        return profitLossAmount;
    }

    public void setProfitLossAmount(Money profitLossAmount) {
        this.profitLossAmount = profitLossAmount;
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

    public String getProfitLossAmountStr() {
        return profitLossAmountStr;
    }

    public void setProfitLossAmountStr(String profitLossAmountStr) {
        this.profitLossAmountStr = profitLossAmountStr;
    }

}