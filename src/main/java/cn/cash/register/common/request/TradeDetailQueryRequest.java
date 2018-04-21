/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.Date;

/**
 * 销售单据分页查询请求类
 * @author HuHui
 * @version $Id: TradeDetailQueryRequest.java, v 0.1 2018年4月21日 上午9:54:59 HuHui Exp $
 */
public class TradeDetailQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -91441128600162585L;

    private String            sellerNo;

    private String            payChenal;

    private String            tradeType;

    /** 时间上限 */
    private Date              tradeTimeUp;

    /** 时间下限 */
    private Date              tradeTimeDown;

    private String            tradeNo;

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getPayChenal() {
        return payChenal;
    }

    public void setPayChenal(String payChenal) {
        this.payChenal = payChenal;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Date getTradeTimeUp() {
        return tradeTimeUp;
    }

    public void setTradeTimeUp(Date tradeTimeUp) {
        this.tradeTimeUp = tradeTimeUp;
    }

    public Date getTradeTimeDown() {
        return tradeTimeDown;
    }

    public void setTradeTimeDown(Date tradeTimeDown) {
        this.tradeTimeDown = tradeTimeDown;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

}
