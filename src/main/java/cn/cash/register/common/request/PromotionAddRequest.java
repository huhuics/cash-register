/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.util.AssertUtil;

/**
 * 促销增加请求类
 * @author HuHui
 * @version $Id: PromotionAddRequest.java, v 0.1 2018年5月23日 下午3:58:42 HuHui Exp $
 */
public class PromotionAddRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = -4405955627877018496L;

    private Long              id;

    private String            promotionName;

    private String            promotionType;

    private Boolean           isMemberOnly;

    private Boolean           isMemberDiscountTwice;

    private String            startTime;

    private String            endTime;

    private Boolean           status;

    private String            gmtUpdate;

    private String            gmtCreate;

    @Override
    public void validate() {
        AssertUtil.assertNotBlank(promotionName, "促销名称不能为空");
        AssertUtil.assertNotBlank(promotionType, "促销类型不能为空");
    }

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
        this.promotionName = promotionName;
    }

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(String gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

}
