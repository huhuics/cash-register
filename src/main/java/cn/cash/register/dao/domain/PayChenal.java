/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

/**
 * 收银时从前台传到后台的支付/退款方式
 * @author HuHui
 * @version $Id: PayChenal.java, v 0.1 2018年4月23日 上午11:24:45 HuHui Exp $
 */
public class PayChenal extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 4401972139057180239L;

    /** 支付/退款方式 */
    private String            chenal;

    /** 金额,单位:元 */
    private String            amount;

    public String getChenal() {
        return chenal;
    }

    public void setChenal(String chenal) {
        this.chenal = chenal;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
