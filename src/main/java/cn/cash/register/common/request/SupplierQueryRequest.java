/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

/**
 * 供货商查询请求类
 * @author HuHui
 * @version $Id: SupplierQueryRequest.java, v 0.1 2018年4月24日 下午8:47:38 HuHui Exp $
 */
public class SupplierQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -1595294356303845168L;

    private boolean           status;

    private String            supplierName;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

}
