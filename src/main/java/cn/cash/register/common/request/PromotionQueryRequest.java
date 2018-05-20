/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 促销分页查询请求类
 * @author HuHui
 * @version $Id: PromotionQueryRequest.java, v 0.1 2018年5月16日 下午2:51:28 HuHui Exp $
 */
public class PromotionQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -5398272861052275960L;

    private String            promotionType;

    private String            status;

    private String            promotionName;

    public String getPromotionType() {
        return promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

}
