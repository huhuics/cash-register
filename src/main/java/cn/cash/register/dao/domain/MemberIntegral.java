package cn.cash.register.dao.domain;

import java.util.Date;

public class MemberIntegral extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -5809989244328786163L;

    private Long              id;

    private Boolean           status;

    private Boolean           integralType;

    private Integer           integralValue;

    private Boolean           exchangeType;

    private Boolean           isClear;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Boolean integralType) {
        this.integralType = integralType;
    }

    public Integer getIntegralValue() {
        return integralValue;
    }

    public void setIntegralValue(Integer integralValue) {
        this.integralValue = integralValue;
    }

    public Boolean getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(Boolean exchangeType) {
        this.exchangeType = exchangeType;
    }

    public Boolean getIsClear() {
        return isClear;
    }

    public void setIsClear(Boolean isClear) {
        this.isClear = isClear;
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