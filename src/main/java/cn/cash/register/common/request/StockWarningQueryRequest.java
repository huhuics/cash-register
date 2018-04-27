/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.common.request;

import cn.cash.register.enums.StockWarningEnum;

/**
 * 库存预警商品查询请求类
 * @author HuHui
 * @version $Id: StockWarningQueryRequest.java, v 0.1 2018年4月26日 下午8:55:10 HuHui Exp $
 */
public class StockWarningQueryRequest extends BasePageRequest {

    /**  */
    private static final long serialVersionUID = -8450810956394168157L;

    private String            categoryName;

    private String            supplierName;

    /**
     * {@link StockWarningEnum}
     */
    private String            warningType;

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getWarningType() {
        return warningType;
    }

    public void setWarningType(String warningType) {
        this.warningType = warningType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
