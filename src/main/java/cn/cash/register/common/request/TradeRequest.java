/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import cn.cash.register.dao.domain.GoodsItem;
import cn.cash.register.dao.domain.PayChenal;
import cn.cash.register.util.AssertUtil;

/**
 * 收银/退款请求类
 * @author HuHui
 * @version $Id: TradeRequest.java, v 0.1 2018年4月23日 上午10:46:31 HuHui Exp $
 */
public class TradeRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -7864023113538102948L;

    private List<GoodsItem>   goodsItems;

    private List<PayChenal>   payChenals;

    private Long              memberId;

    private String            memberName;

    private String            sellerNo;

    private String            shopperNo;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(goodsItems), "收银商品不能为空");
        AssertUtil.assertTrue(CollectionUtils.isNotEmpty(payChenals), "支付方式不能为空");
    }

    public List<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public List<PayChenal> getPayChenals() {
        return payChenals;
    }

    public void setPayChenals(List<PayChenal> payChenals) {
        this.payChenals = payChenals;
    }

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getShopperNo() {
        return shopperNo;
    }

    public void setShopperNo(String shopperNo) {
        this.shopperNo = shopperNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

}
