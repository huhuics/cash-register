/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 增加商品报损记录请求类
 * @author HuHui
 * @version $Id: GoodsLoseInfoRequest.java, v 0.1 2018年4月26日 下午7:20:37 HuHui Exp $
 */
public class GoodsLoseInfoAddRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -675696658279069420L;

    private Long              id;

    private String            shopName;

    private String            loseItemsStr;

    private Double            turnoverPercent;

    private String            operatorNo;

    private String            remark;

    @Override
    public void validate() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Double getTurnoverPercent() {
        return turnoverPercent;
    }

    public void setTurnoverPercent(Double turnoverPercent) {
        this.turnoverPercent = turnoverPercent;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLoseItemsStr() {
        return loseItemsStr;
    }

    public void setLoseItemsStr(String loseItemsStr) {
        this.loseItemsStr = loseItemsStr;
    }

}
