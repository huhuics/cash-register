/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.dao.domain;

import java.util.List;

/**
 * 购物小票
 * @author HuHui
 * @version $Id: ShoppingReceipt.java, v 0.1 2018年5月25日 下午3:24:51 HuHui Exp $
 */
public class ShoppingReceipt extends BaseDomain {

    /**  */
    private static final long      serialVersionUID = -2580794795916480271L;

    private String                 tradeNo;

    private String                 tradeTime;

    private String                 memberNo;

    private String                 goodsCount;

    private String                 totalAmount;

    private String                 goodsDiscount;

    private String                 totalActualAmount;

    private String                 sellerNo;

    private List<TradeGoodsDetail> goodsInfo;
}
