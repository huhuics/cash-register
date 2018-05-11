/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import cn.cash.register.util.Money;

/**
 * 销售概况
 * @author HuHui
 * @version $Id: SalesBasicFacts.java, v 0.1 2018年5月10日 下午9:16:55 HuHui Exp $
 */
public class SalesBasicFacts extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 7066857195226037106L;

    /** 概况 */
    private String            basicFacts;

    /** 现金 */
    private Money             cash;

    /** 储值卡 */
    private Money             balance;

    private Money             unionpay;

    private Money             alipay;

    private Money             wcpay;

    public String getBasicFacts() {
        return basicFacts;
    }

    public void setBasicFacts(String basicFacts) {
        this.basicFacts = basicFacts;
    }

    public Money getCash() {
        return cash;
    }

    public void setCash(Money cash) {
        this.cash = cash;
    }

    public Money getBalance() {
        return balance;
    }

    public void setBalance(Money balance) {
        this.balance = balance;
    }

    public Money getUnionpay() {
        return unionpay;
    }

    public void setUnionpay(Money unionpay) {
        this.unionpay = unionpay;
    }

    public Money getAlipay() {
        return alipay;
    }

    public void setAlipay(Money alipay) {
        this.alipay = alipay;
    }

    public Money getWcpay() {
        return wcpay;
    }

    public void setWcpay(Money wcpay) {
        this.wcpay = wcpay;
    }

}
