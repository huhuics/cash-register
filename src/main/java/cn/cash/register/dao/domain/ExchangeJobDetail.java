package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class ExchangeJobDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -915828387207934164L;

    private Long              id;

    private Date              startTime;

    private Date              endTime;

    private String            sellerNo;

    private Money             checkoutTotalAmount;

    private Money             cashAmount;

    private Money             balanceAmount;

    private Money             unionpayAmount;

    private Money             alipayAmount;

    private Money             wcpayAmount;

    private Money             pettyCashAmount;

    private Money             paidAmount;

    private Boolean           isfinished;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public ExchangeJobDetail() {
    }

    public ExchangeJobDetail(Date startTime, String sellerNo, Date gmtCreate) {
        this.startTime = startTime;
        this.sellerNo = sellerNo;
        this.gmtCreate = gmtCreate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo == null ? null : sellerNo.trim();
    }

    public Money getCheckoutTotalAmount() {
        return checkoutTotalAmount;
    }

    public void setCheckoutTotalAmount(Money checkoutTotalAmount) {
        this.checkoutTotalAmount = checkoutTotalAmount;
    }

    public Money getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(Money cashAmount) {
        this.cashAmount = cashAmount;
    }

    public Money getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(Money balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Money getUnionpayAmount() {
        return unionpayAmount;
    }

    public void setUnionpayAmount(Money unionpayAmount) {
        this.unionpayAmount = unionpayAmount;
    }

    public Money getAlipayAmount() {
        return alipayAmount;
    }

    public void setAlipayAmount(Money alipayAmount) {
        this.alipayAmount = alipayAmount;
    }

    public Money getWcpayAmount() {
        return wcpayAmount;
    }

    public void setWcpayAmount(Money wcpayAmount) {
        this.wcpayAmount = wcpayAmount;
    }

    public Money getPettyCashAmount() {
        return pettyCashAmount;
    }

    public void setPettyCashAmount(Money pettyCashAmount) {
        this.pettyCashAmount = pettyCashAmount;
    }

    public Money getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Money paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Boolean getIsfinished() {
        return isfinished;
    }

    public void setIsfinished(Boolean isfinished) {
        this.isfinished = isfinished;
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