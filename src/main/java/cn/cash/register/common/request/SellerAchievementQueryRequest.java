/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 收银员业绩查询
 * @author HuHui
 * @version $Id: SellerAchievementQueryRequest.java, v 0.1 2018年5月3日 上午10:24:57 HuHui Exp $
 */
public class SellerAchievementQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 519519037698009022L;

    private String            sellerNo;

    private String            categoryName;

    private String            tradeTimeUp;

    private String            tradeTimeDown;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(tradeTimeUp, "查询时间不能为空");
        AssertUtil.assertNotBlank(tradeTimeDown, "查询时间不能为空");
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTradeTimeUp() {
        return tradeTimeUp;
    }

    public void setTradeTimeUp(String tradeTimeUp) {
        this.tradeTimeUp = tradeTimeUp;
    }

    public String getTradeTimeDown() {
        return tradeTimeDown;
    }

    public void setTradeTimeDown(String tradeTimeDown) {
        this.tradeTimeDown = tradeTimeDown;
    }

}
