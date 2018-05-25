/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.test.printer;

import cn.cash.register.dao.domain.BaseDomain;

/**
 * 商品类
 * @author HuHui
 * @version $Id: Commodity.java, v 0.1 2018年5月25日 上午11:07:13 HuHui Exp $
 */
public class Commodity extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 4976665178410655209L;

    // 商品名称  
    private String            name;

    // 单价  
    private String            unitPrice;

    // 数量  
    private String            num;

    // 总价  
    private String            sum;

    // 条码  
    private String            barcode;

    public Commodity(String name, String unitPrice, String num, String sum, String barcode) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.num = num;
        this.sum = sum;
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

}
