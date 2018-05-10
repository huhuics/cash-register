package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class MemberRechargeDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -5399110005547854868L;

    private Long              id;

    private String            sellerNo;

    private String            shopperNo;

    private Money             rechargeAmount;

    private Money             donationAmount;

    private Money             totalAmount;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Money getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Money rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Money getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(Money donationAmount) {
        this.donationAmount = donationAmount;
    }

    public Money getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Money totalAmount) {
        this.totalAmount = totalAmount;
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