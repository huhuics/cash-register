/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;

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

    /***********************Controller填充以下参数***************************/

    private String            goodsItemsJSONStr;

    private String            payChenalsJSONStr;

    private Long              memberId;

    private String            memberNo;

    private String            memberName;

    private String            sellerNo;

    private String            shopperNo;

    private String            change;

    private Long              exchangeJobId;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(sellerNo, "收银员编号不能为空");
        AssertUtil.assertNotBlank(goodsItemsJSONStr, "收银商品不能为空");
        AssertUtil.assertNotBlank(payChenalsJSONStr, "支付方式不能为空");
        AssertUtil.assertNotNull(exchangeJobId, "交接班id不能为空");

        convert();
    }

    private void convert() {
        //参数转化
        goodsItems = JSON.parseArray(goodsItemsJSONStr, GoodsItem.class);
        payChenals = JSON.parseArray(payChenalsJSONStr, PayChenal.class);
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

    public List<GoodsItem> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(ArrayList<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public List<PayChenal> getPayChenals() {
        return payChenals;
    }

    public void setPayChenals(ArrayList<PayChenal> payChenals) {
        this.payChenals = payChenals;
    }

    public String getGoodsItemsJSONStr() {
        return goodsItemsJSONStr;
    }

    public void setGoodsItemsJSONStr(String goodsItemsJSONStr) {
        this.goodsItemsJSONStr = goodsItemsJSONStr;
    }

    public String getPayChenalsJSONStr() {
        return payChenalsJSONStr;
    }

    public void setPayChenalsJSONStr(String payChenalsJSONStr) {
        this.payChenalsJSONStr = payChenalsJSONStr;
    }

    public Long getExchangeJobId() {
        return exchangeJobId;
    }

    public void setExchangeJobId(Long exchangeJobId) {
        this.exchangeJobId = exchangeJobId;
    }

    public void setGoodsItems(List<GoodsItem> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public void setPayChenals(List<PayChenal> payChenals) {
        this.payChenals = payChenals;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

}
