package cn.cash.register.dao.domain;

import java.util.Date;

public class PromotionDetail extends BaseDomain {
    /**  */
    private static final long serialVersionUID = 6004981001681516431L;

    private Long              id;

    private String            promotionName;

    private String            promotionType;

    private Boolean           isMemberOnly;

    private Boolean           isMemberDiscountTwice;

    private Date              startTime;

    private Date              endTime;

    private Boolean           status;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    private String            detail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName == null ? null : promotionName.trim();
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType == null ? null : promotionType.trim();
    }

    public Boolean getIsMemberOnly() {
        return isMemberOnly;
    }

    public void setIsMemberOnly(Boolean isMemberOnly) {
        this.isMemberOnly = isMemberOnly;
    }

    public Boolean getIsMemberDiscountTwice() {
        return isMemberDiscountTwice;
    }

    public void setIsMemberDiscountTwice(Boolean isMemberDiscountTwice) {
        this.isMemberDiscountTwice = isMemberDiscountTwice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}