package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class TradeDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 4762764775503457325L;

    private Long              id;

    private String            tradeNo;

    private Date              tradeTime;

    private String            tradeType;

    private String            memberName;

    private Integer           goodsCount;

    private Money             totalAmount;

    private Integer           goodsDiscount;

    private Money             totalActualAmount;

    private Money             profitAmount;

    private String            sellerNo;

    private String            shopperNo;

    private String            goodsDetail;

    private String            payChenal;

    private Boolean           isExchangeJob;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public TradeDetail() {
    }

    public TradeDetail(String tradeNo, Date tradeTime, String tradeType) {
        this.tradeNo = tradeNo;
        this.tradeTime = tradeTime;
        this.tradeType = tradeType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo == null ? null : tradeNo.trim();
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getGoodsDiscount() {
        return goodsDiscount;
    }

    public void setGoodsDiscount(Integer goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    public Money getTotalActualAmount() {
        return totalActualAmount;
    }

    public void setTotalActualAmount(Money totalActualAmount) {
        this.totalActualAmount = totalActualAmount;
    }

    public Money getProfitAmount() {
        return profitAmount;
    }

    public void setProfitAmount(Money profitAmount) {
        this.profitAmount = profitAmount;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public String getShopperNo() {
        return shopperNo;
    }

    public void setShopperNo(String shopperNo) {
        this.shopperNo = shopperNo == null ? null : shopperNo.trim();
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail == null ? null : goodsDetail.trim();
    }

    public String getPayChenal() {
        return payChenal;
    }

    public void setPayChenal(String payChenal) {
        this.payChenal = payChenal == null ? null : payChenal.trim();
    }

    public Boolean getIsExchangeJob() {
        return isExchangeJob;
    }

    public void setIsExchangeJob(Boolean isExchangeJob) {
        this.isExchangeJob = isExchangeJob;
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