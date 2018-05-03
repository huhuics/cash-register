/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 导购员资料查询请求类
 * @author HuHui
 * @version $Id: ShopperInfoQueryRequest.java, v 0.1 2018年5月3日 上午11:37:01 HuHui Exp $
 */
public class ShopperInfoQueryRequest extends BaseRequest {

    /**  */
    private static final long serialVersionUID = 4028182476777388723L;

    private Boolean           status;

    private String            shopperNo;

    private String            name;

    @Override
    public void validate() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getShopperNo() {
        return shopperNo;
    }

    public void setShopperNo(String shopperNo) {
        this.shopperNo = shopperNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
