/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 收银员查询请求
 * @author HuHui
 * @version $Id: SellerInfoQueryRequest.java, v 0.1 2018年4月18日 下午5:20:40 HuHui Exp $
 */
public class SellerInfoQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = 144932071760932222L;

    private String            sellerNo;

    private String            name;

    private String            phone;

    private boolean           status;

    public String getSellerNo() {
        return sellerNo;
    }

    public void setSellerNo(String sellerNo) {
        this.sellerNo = sellerNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
