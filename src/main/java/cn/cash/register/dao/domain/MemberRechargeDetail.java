package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class MemberRechargeDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -8568248259703257470L;

    private Long              id;

    private String            memberNo;

    private String            memberName;

    private String            rankTitle;

    private String            sellerNo;

    private String            shopperNo;

    private Money             rechargeAmount;

    private Money             donationAmount;

    private Money             totalAmount;

    private String            payChenal;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo == null ? null : memberNo.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getRankTitle() {
        return rankTitle;
    }

    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle == null ? null : rankTitle.trim();
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

    public String getPayChenal() {
        return payChenal;
    }

    public void setPayChenal(String payChenal) {
        this.payChenal = payChenal == null ? null : payChenal.trim();
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