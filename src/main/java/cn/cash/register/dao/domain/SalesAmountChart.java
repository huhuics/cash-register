/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import cn.cash.register.util.Money;

/**
 * 营业数据图表显示类
 * @author HuHui
 * @version $Id: SalesAmountChart.java, v 0.1 2018年5月15日 下午8:56:41 HuHui Exp $
 */
public class SalesAmountChart extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 3854921107083137279L;

    /**
     * 横轴,代表时间
     */
    private String            time;

    private Money             totalActualAmount;

    private Money             profitAmount;

    private Money             goodsCount;

    /**
     * 毛利率
     */
    private Double            profitRate;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Money getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Money goodsCount) {
        this.goodsCount = goodsCount;
    }

    public Double getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Double profitRate) {
        this.profitRate = profitRate;
    }

}
