package cn.cash.register.dao.domain;

import java.util.Date;

public class GoodsLoseReason extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -6954659391763945176L;

    private Long              id;

    private String            reason;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public GoodsLoseReason() {
    }

    public GoodsLoseReason(String reason) {
        this.reason = reason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
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