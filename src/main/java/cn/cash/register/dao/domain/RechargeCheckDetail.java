/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import cn.cash.register.util.Money;

/**
 * 充值对账结果
 * @author HuHui
 * @version $Id: RechargeCheckDetail.java, v 0.1 2018年5月14日 下午8:08:05 HuHui Exp $
 */
public class RechargeCheckDetail extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 4388925274032376277L;

    private String            memberNo;

    private String            memberName;

    private Money             rechargeAmount;

    private Money             donationAmount;

    private Money             consumeAmount;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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

    public Money getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(Money consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

}
