package cn.cash.register.dao.domain;

import java.util.Date;

import cn.cash.register.util.Money;

public class GoodsLoseInfo extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -1249307628715056314L;

    private Long              id;

    private String            shopName;

    private String            goodsDetail;

    private Money             totalLoseAmount;

    private Double            turnoverPercent;

    private String            operatorNo;

    private String            remark;

    private Date              gmtUpdate;

    private Date              gmtCreate;

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
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(String goodsDetail) {
        this.goodsDetail = goodsDetail == null ? null : goodsDetail.trim();
    }

    public Money getTotalLoseAmount() {
        return totalLoseAmount;
    }

    public void setTotalLoseAmount(Money totalLoseAmount) {
        this.totalLoseAmount = totalLoseAmount;
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
        this.operatorNo = operatorNo == null ? null : operatorNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}