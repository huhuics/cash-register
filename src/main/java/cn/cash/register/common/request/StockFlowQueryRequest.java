/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 商品库存查询请求类
 * @author HuHui
 * @version $Id: StockFlowQueryRequest.java, v 0.1 2018年4月26日 上午10:42:57 HuHui Exp $
 */
public class StockFlowQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 6536539158742873073L;

    private String            flowType;

    private String            goodsName;

    private String            barCode;

    private String            gmtCreateUp;

    private String            gmtCreateDown;

    @Override
    public void validate() {
    }

    public String getFlowType() {
        return flowType;
    }

    public void setFlowType(String flowType) {
        this.flowType = flowType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
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
