/**
 * Cash-Register
 * Copyright (c) 1995-2018 All Rights Reserved.
 */
package cn.cash.register.printer;

import java.io.File;

import cn.cash.register.dao.domain.BaseDomain;

/**
 * 商品标签
 * @author HuHui
 * @version $Id: GoodsLabel.java, v 0.1 2018年5月27日 下午3:12:43 HuHui Exp $
 */
public class GoodsLabel extends BaseDomain {

    /**  */
    private static final long serialVersionUID = 8059764029066222137L;

    private String            goodsName;

    private String            barCode;

    private File              barCodeFile;

    private String            goodsColor;

    private String            goodsSize;

    private String            salesPrice;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getGoodsColor() {
        return goodsColor;
    }

    public void setGoodsColor(String goodsColor) {
        this.goodsColor = goodsColor;
    }

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    public String getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(String salesPrice) {
        this.salesPrice = salesPrice;
    }

    public File getBarCodeFile() {
        return barCodeFile;
    }

    public void setBarCodeFile(File barCodeFile) {
        this.barCodeFile = barCodeFile;
    }

}
