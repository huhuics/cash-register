package cn.cash.register.dao.domain;

import java.util.Date;

public class MemberRank extends BaseDomain {
    /**  */
    private static final long serialVersionUID = -903259649306307735L;

    private Long              id;

    private String            rankTitle;

    private Integer           discount;

    private Boolean           isIntegral;

    private Boolean           isAutoUpgrade;

    private Integer           integralToUpgrade;

    private Boolean           rankPeriod;

    private String            prepaidCardNo;

    private Date              gmtUpdate;

    private Date              gmtCreate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRankTitle() {
        return rankTitle;
    }

    public void setRankTitle(String rankTitle) {
        this.rankTitle = rankTitle == null ? null : rankTitle.trim();
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Boolean getIsIntegral() {
        return isIntegral;
    }

    public void setIsIntegral(Boolean isIntegral) {
        this.isIntegral = isIntegral;
    }

    public Boolean getIsAutoUpgrade() {
        return isAutoUpgrade;
    }

    public void setIsAutoUpgrade(Boolean isAutoUpgrade) {
        this.isAutoUpgrade = isAutoUpgrade;
    }

    public Integer getIntegralToUpgrade() {
        return integralToUpgrade;
    }

    public void setIntegralToUpgrade(Integer integralToUpgrade) {
        this.integralToUpgrade = integralToUpgrade;
    }

    public Boolean getRankPeriod() {
        return rankPeriod;
    }

    public void setRankPeriod(Boolean rankPeriod) {
        this.rankPeriod = rankPeriod;
    }

    public String getPrepaidCardNo() {
        return prepaidCardNo;
    }

    public void setPrepaidCardNo(String prepaidCardNo) {
        this.prepaidCardNo = prepaidCardNo == null ? null : prepaidCardNo.trim();
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