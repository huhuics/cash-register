/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import cn.cash.register.util.AssertUtil;

/**
 * 会员余额充值请求类
 * @author HuHui
 * @version $Id: MemberRechargeRequest.java, v 0.1 2018年5月10日 下午7:22:02 HuHui Exp $
 */
public class MemberRechargeRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -139367097968406306L;

    private String            memberNo;

    private String            shopperName;

    /**
     * 实际充值金额
     */
    private String            rechargeAmount;

    /**
     * 赠送金额
     */
    private String            donationAmount;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(memberNo, "会员号不能为空");
        AssertUtil.assertTrue(NumberUtils.isDigits(rechargeAmount), "充值金额错误");
        if (StringUtils.isNotBlank(donationAmount)) {
            AssertUtil.assertTrue(NumberUtils.isDigits(donationAmount), "赠送金额错误");
        }
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getShopperName() {
        return shopperName;
    }

    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }

    public String getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(String rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public String getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(String donationAmount) {
        this.donationAmount = donationAmount;
    }

}
