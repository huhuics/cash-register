/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.Date;

/**
 * 查询需要交接班的交易详情请求类
 * @author HuHui
 * @version $Id: ExchangeJobTradeDetailRequest.java, v 0.1 2018年5月2日 下午9:40:30 HuHui Exp $
 */
public class ExchangeJobTradeDetailRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 7273901886163625542L;

    private Date              startTime;

    private Long              exchangeJobId;

    private String            sellerNo;

    public ExchangeJobTradeDetailRequest(Date startTime, Long exchangeJobId, String sellerNo) {
        this.startTime = startTime;
        this.exchangeJobId = exchangeJobId;
        this.sellerNo = sellerNo;
    }

    public ExchangeJobTradeDetailRequest() {
    }

    @Override
    public void validate() {

    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Long getExchangeJobId() {
        return exchangeJobId;
    }

    public void setExchangeJobId(Long exchangeJobId) {
        this.exchangeJobId = exchangeJobId;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

}
