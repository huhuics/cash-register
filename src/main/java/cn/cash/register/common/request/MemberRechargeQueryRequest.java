/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.enums.PayChenalEnum;
import cn.cash.register.util.AssertUtil;

/**
 * 会员充值查询请求类
 * @author HuHui
 * @version $Id: MemberRechargeQueryRequest.java, v 0.1 2018年5月14日 下午7:41:29 HuHui Exp $
 */
public class MemberRechargeQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -8540287736208186272L;

    /** 收银员编号 */
    private String            sellerNo;

    /** 支付方式{@link PayChenalEnum} */
    private String            payChanel;

    private String            gmtCreateUp;

    private String            gmtCreateDown;

    @Override
    public void validate() {
        super.validate();
        AssertUtil.assertNotBlank(gmtCreateUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(gmtCreateDown, "查询时间不能为空");
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getPayChanel() {
        return payChanel;
    }

    public void setPayChanel(String payChanel) {
        this.payChanel = payChanel;
    }

    public String getGmtCreateUp() {
        return gmtCreateUp;
    }

    public void setGmtCreateUp(String gmtCreateUp) {
        this.gmtCreateUp = gmtCreateUp;
    }

    public String getGmtCreateDown() {
        return gmtCreateDown;
    }

    public void setGmtCreateDown(String gmtCreateDown) {
        this.gmtCreateDown = gmtCreateDown;
    }

}
